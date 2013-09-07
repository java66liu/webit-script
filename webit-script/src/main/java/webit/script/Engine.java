// Copyright (c) 2013, Webit Team. All Rights Reserved.
package webit.script;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import jodd.petite.PetiteContainer;
import jodd.petite.meta.PetiteInitMethod;
import jodd.props.Props;
import jodd.util.StringUtil;
import webit.script.core.text.TextStatmentFactory;
import webit.script.exceptions.ResourceNotFoundException;
import webit.script.filters.Filter;
import webit.script.io.charset.CoderFactory;
import webit.script.loaders.Loader;
import webit.script.loggers.Logger;
import webit.script.resolvers.Resolver;
import webit.script.resolvers.ResolverManager;
import webit.script.security.NativeSecurityManager;
import webit.script.util.PropsUtil;

/**
 *
 * @author Zqq
 */
public final class Engine {

    public static final String ENGINE = "engine";
    public static final String PETITE = "petite";
    //
    private static final String DEFAULT_PROPERTIES = "/webitl-default.props";
    //settings
    private Class resourceLoaderClass = webit.script.loaders.impl.ClasspathLoader.class;
    private Class textStatmentFactoryClass = webit.script.core.text.impl.SimpleTextStatmentFactory.class;
    private Class nativeSecurityManagerClass = webit.script.security.impl.DefaultNativeSecurityManager.class;
    private Class coderFactoryClass = webit.script.io.charset.impl.DefaultCoderFactory.class;
    private Class filterClass;
    private Class loggerClass = webit.script.loggers.impl.NOPLogger.class;
    private Class[] resolvers;
    private String encoding = "UTF-8";
    private boolean enableAsmNative = true;
    private boolean looseVar = false;
    //
    private Logger logger;
    private Filter filter;
    private TextStatmentFactory textStatmentFactory;
    private NativeSecurityManager nativeSecurityManager;
    private CoderFactory coderFactory;
    private final ResolverManager resolverManager;
    private Loader resourceLoader;
    private final ConcurrentMap<String, Template> templateCache;
    private final PetiteContainer _petite;

    public Engine(PetiteContainer petite) {
        this._petite = petite;
        this.templateCache = new ConcurrentHashMap<String, Template>();
        this.resolverManager = new ResolverManager();
    }

    @PetiteInitMethod
    public void init() throws Exception {

        this.resourceLoader = (Loader) getBean(this.resourceLoaderClass);
        this.textStatmentFactory = (TextStatmentFactory) getBean(this.textStatmentFactoryClass);
        this.nativeSecurityManager = (NativeSecurityManager) getBean(this.nativeSecurityManagerClass);
        this.coderFactory = (CoderFactory) getBean(this.coderFactoryClass);
        
        this.logger = (Logger) getBean(this.loggerClass);

        if (this.filterClass != null) {
            this.filter = (Filter) getBean(this.filterClass);
        }

        resolveBean(this.resolverManager);
        if (this.resolvers != null) {
            Resolver[] resolverInstances = new Resolver[this.resolvers.length];
            for (int i = 0; i < this.resolvers.length; i++) {
                resolverInstances[i] = (Resolver) getBean(this.resolvers[i]);
            }
            this.resolverManager.init(resolverInstances);
        }
    }

    public void resolveBean(Object bean) throws InstantiationException, IllegalAccessException {

        Class type = bean.getClass();
        String beanName = _petite.resolveBeanName(type);
        if (_petite.getBean(beanName) != bean) {

            _petite.addBean(beanName, bean);

            if (bean instanceof Configable) {
                ((Configable) bean).init(this);
            }
        }
    }

    public <E> E getBean(Class<E> type) throws InstantiationException, IllegalAccessException {

        String beanName = _petite.resolveBeanName(type);
        Object object = _petite.getBean(beanName);
        if (object == null) {
            object = type.newInstance();
            resolveBean(object);
        }
        return (E) object;
    }

    public Template getTemplate(String parentName, String name) throws ResourceNotFoundException {
        return getTemplate(resourceLoader.concat(parentName, name));
    }

    public Template getTemplate(String name) throws ResourceNotFoundException {
        String normalizedName = resourceLoader.normalize(name);
        if (normalizedName == null) {
            throw new ResourceNotFoundException("TODO: 不合法的模板名:" + name);
        }
        Template template = templateCache.get(normalizedName);
        if (template == null) {
            template = new Template(this, normalizedName, resourceLoader.get(normalizedName)); //fast
            Template oldTemplate = templateCache.putIfAbsent(normalizedName, template);
            if (oldTemplate != null) {
                template = oldTemplate;
            }
        }
        return template;
    }

    public void setResourceLoaderClass(Class resourceLoaderClass) {
        this.resourceLoaderClass = resourceLoaderClass;
    }

    public void setTextStatmentFactoryClass(Class textStatmentFactoryClass) {
        this.textStatmentFactoryClass = textStatmentFactoryClass;
    }

    public void setResourceLoader(Loader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean isEnableAsmNative() {
        return enableAsmNative;
    }

    public void setEnableAsmNative(boolean enableAsmNative) {
        this.enableAsmNative = enableAsmNative;
    }

    public boolean isLooseVar() {
        return looseVar;
    }

    public void setLooseVar(boolean looseVar) {
        this.looseVar = looseVar;
    }

    public NativeSecurityManager getNativeSecurityManager() {
        return nativeSecurityManager;
    }

    public ResolverManager getResolverManager() {
        return resolverManager;
    }

    public TextStatmentFactory getTextStatmentFactory() {
        return textStatmentFactory;
    }

    public CoderFactory getCoderFactory() {
        return coderFactory;
    }

    public void setFilterClass(Class filterClass) {
        this.filterClass = filterClass;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setLoggerClass(Class loggerClass) {
        this.loggerClass = loggerClass;
    }

    public Logger getLogger() {
        return logger;
    }

    public static Engine createEngine(String configPath) {
        return createEngine(configPath, null);
    }

    public static Engine createEngine(String configPath, Map parameters) {

        PetiteContainer petite = new PetiteContainer();
        petite.getConfig().setUseFullTypeNames(true);

        final Props props = new Props();
        //props.loadSystemProperties("sys");
        //props.loadEnvironment("env");

        List<String> propsFiles;
        if (configPath != null) {
            propsFiles = PropsUtil.loadFromClasspath(props, DEFAULT_PROPERTIES, configPath);
        } else {
            propsFiles = PropsUtil.loadFromClasspath(props, DEFAULT_PROPERTIES);
        }

        if (parameters != null) {
            props.load(parameters);
        }

        petite.defineParameters(props);
        petite.addBean(PETITE, petite);

        final Engine engine = new Engine(petite);

        String engineBeanName = petite.resolveBeanName(Engine.class);
        petite.addBean(engineBeanName, engine);
        petite.addBean(ENGINE, engine);
        
        //Log props file name
        Logger logger = engine.getLogger();
        if (logger != null && logger.isInfoEnabled()) {
            logger.info("Loaded props files from classpath: {}", StringUtil.join(propsFiles, ", "));
        }

        return engine;
    }
}
