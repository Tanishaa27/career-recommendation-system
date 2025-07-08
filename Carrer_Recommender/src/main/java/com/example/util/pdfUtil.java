package com.example.util;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class pdfUtil {

    public static ByteArrayOutputStream generatePdfFromHtml(String htmlBody) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // ✅ XHTML-compliant full HTML document
        String fullHtml = "<!DOCTYPE html>" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                "<head>" +
                "<meta charset='UTF-8' />" +  // <-- fixed self-closing tag
                "<style>" +
                "body { font-family: Arial, sans-serif; padding: 20px; }" +
                "h1, h2, h3, h4, h5 { color: #333; }" +
                "ul { padding-left: 20px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                htmlBody +
                "</body>" +
                "</html>";

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(fullHtml, null);
        builder.toStream(outputStream);
        builder.run();

        return outputStream;
    }
}
