package com.project.reporting.reporting.report;

public abstract class HtmlReportImpl implements Report {
    public String generateHeader() {
        StringBuilder html = new StringBuilder();
        html.append("<!doctype html>\n");
        html.append("<html lang='en'>\n");

        html.append("<head>\n");
        html.append("<meta charset='utf-8'>\n");
        html.append("<title>");
        html.append(getFullReportName());
        html.append("</title>\n");
        html.append("</head>\n\n");
        html.append("<body>\n");

        return html.toString();
    }

    @Override
    public String generate() {
        StringBuilder result = new StringBuilder();
        result.append(generateHeader());
        result.append(generateBody());
        result.append(generateFooter());

        return result.toString();
    }

    protected abstract String generateBody();

    public String generateFooter() {
        StringBuilder html = new StringBuilder();
        html.append("</body>\n\n");
        html.append("</html>");

        return html.toString();
    }
}
