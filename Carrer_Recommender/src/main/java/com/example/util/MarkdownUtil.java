package com.example.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;

public class MarkdownUtil {
	public static String parseMarkdownToHtml(String filePath) throws IOException {
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        String markdown = Files.readString(Paths.get(filePath));
        Node document = parser.parse(markdown);
        return renderer.render(document);
}
}
