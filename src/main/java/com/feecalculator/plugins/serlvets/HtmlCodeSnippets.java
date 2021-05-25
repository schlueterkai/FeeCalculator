package com.feecalculator.plugins.serlvets;

public class HtmlCodeSnippets {

    public static String basicStructureWithNavigationBar = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <title>Transaktionsgebühren</title>\n" + "    <meta charset=\"utf-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n" +
            "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n" +
            "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n" + "</head>\n" + "<body>\n" + "\n" + "<nav class=\"navbar navbar-default\">\n" +
            "    <div class=\"container-fluid\">\n" + "        <div class=\"navbar-header\">\n" + "            <a class=\"navbar-brand\" href=\"#\">Transaktionsgebührenrechner</a>\n" +
            "        </div>\n" + "        <ul class=\"nav navbar-nav\">\n" + "            <li><a href=\"index.html\">Übersicht</a></li>\n" +
            "            <li><a href=\"fee.html\">Transaktionsgebühr berechnen</a></li>\n" + "            <li><a href=\"reversefee.html\">Restbetrag berechnen</a></li>\n" +
            "            <li><a href=\"comparefee.html\">Transaktionsgebühren vergleichen</a></li>\n" + "        </ul>\n" + "    </div>\n" + "</nav>";
    public static String basicStructureEnd = "</body>\n" + "</html>\n";
    public static String compareStructure = "";
    public static String transactionFeeStructure = "";
    public static String reverseFeeStructure = "";
}
