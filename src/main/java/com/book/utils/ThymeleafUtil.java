package com.book.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;

public class ThymeleafUtil {

    // 声明一个静态且不可变的模板引擎引用，类加载后唯一实例
    private static final TemplateEngine engine;

    static {
        // 创建 TemplateEngine 实例
        engine = new TemplateEngine();

        // 使用 ClassLoaderTemplateResolver 从 classpath 加载模板（例如放在 resources/templates 下）
        ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();

        // 指定模板文件的字符编码，必须与模板文件实际编码一致，通常为 UTF-8
        r.setCharacterEncoding("UTF-8");

        // 把解析器配置到引擎上
        engine.setTemplateResolver(r);
    }

    /**
     * 渲染模板并写入指定的 Writer。
     * <p>
     * 参数:
     * - template: 要渲染的模板名或路径（相对于类路径），例如 "login" 或 "templates/login.html"（取决于解析器配置）。
     * - context: Thymeleaf 的上下文实现，包含模板变量（通常使用 org.thymeleaf.context.Context）。
     * - writer: 输出目标（例如 HttpServletResponse.getWriter()）。方法不会关闭 writer；调用方负责管理资源。
     */
    public static void process(String template, IContext context, Writer writer) {
        // 使用共享的 engine 渲染模板并把输出写到传入的 writer 中。
        // 注意：不在此方法内关闭 writer，资源管理（关闭/刷新）由调用方负责。
        engine.process(template, context, writer);
    }


}
