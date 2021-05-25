package com.feecalculator.plugins.serlvets;

public class HtmlCodeSnippets {

    public static String BASIC_STRUCTURE_WITH_NAVIGATION_BAR =
            "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <title>Transaktionsgebühren</title>\n" + "    <meta charset=\"utf-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n" +
                    "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n" +
                    "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n" + "</head>\n" + "<body>\n" + "\n" +
                    "<nav class=\"navbar navbar-default\">\n" + "    <div class=\"container-fluid\">\n" + "        <div class=\"navbar-header\">\n" +
                    "            <a class=\"navbar-brand\" href=\"#\">Transaktionsgebührenrechner</a>\n" + "        </div>\n" + "        <ul class=\"nav navbar-nav\">\n" +
                    "            <li><a href=\"index.html\">Übersicht</a></li>\n" + "            <li><a href=\"fee.html\">Transaktionsgebühr berechnen</a></li>\n" +
                    "            <li><a href=\"reversefee.html\">Restbetrag berechnen</a></li>\n" + "            <li><a href=\"comparefee.html\">Transaktionsgebühren vergleichen</a></li>\n" +
                    "        </ul>\n" + "    </div>\n" + "</nav>";
    public static String BASIC_STRUCTURE_END = "</body>\n" + "</html>\n";
    public static String COMPARE_STRUCTURE = "<div class=\"container\">\n" + "    <h3>Transaktionsgebühren vergleichen</h3>\n" + "    <div class=\"alert alert-success\" role=\"alert\">\n" +
            "        Es konnte erfolgreich die beste Transaktionsart für deine hochgeladene CSV-Datei berchnet werden.\n" + "    </div>\n" + "    <div class=\"alert alert-info\" role=\"alert\">\n" +
            "        Wichtig! Bitte beachte, dass nicht alle möglichen Paypal- und Visatransaktionsarten in dieser Berechnung berücksichtigt wurden. Sowohl die Paypal Zahlung an Freunde und Familie" +
            " als auch Spenden\n" + "        sammeln, werden für die Auswahl der passenden Transaktionsart nicht berücksichtigt.\n" + "    </div>\n" +
            "    <p>Für deine hochgeladene CSV-Datei ist es am günstigsten die Transaktionsart %s zu nutzen. Wenn du diese Transaktionsart für die Transaktionen der Datei genutzt hättest, wären " +
            "Transaktionsgebühren\n" + "    in der Höhe von %s fällig gewesen</p>\n" + "</div>";
    public static String COMPARE_ERROR_INVALID = "<div class=\"container\">\n" + "    <h3>Transaktionsgebühren vergleichen</h3>\n" + "    <div class=\"alert alert-danger\" role=\"alert\">\n" +
            "        Bei der Verarbeitung deiner hochgeladenen Datei ist ein Fehler aufgetreten. Bitte stelle sicher, dass deine Datei in dem richtigen Format vorliegt und die UUID dem Java " +
            "Standard entsprechen.\n" + "        Für mehr Informationen zum Aufbau der Datei schaue auf <a href=\"https://github.com/schlueterkai/FeeCalculator\">Github</a> vorbei.\n" +
            "    </div>\n" + "</div>";
    public static String COMPARE_ERROR_EMPTY = "<div class=\"container\">\n" + "    <h3>Transaktionsgebühren vergleichen</h3>\n" + "    <div class=\"alert alert-danger\" role=\"alert\">\n" +
            "        Bei der Verarbeitung deiner hochgeladenen Datei ist ein Fehler aufgetreten. Es konnten keine Transaktionen in der Datei gefunden werden.\n" +
            "        Bitte stelle sicher, dass deine Datei in dem richtigen Format vorliegt.\n" +
            "        Für mehr Informationen zum Aufbau der Datei schaue auf <a href=\"https://github.com/schlueterkai/FeeCalculator\">Github</a> vorbei.\n" + "    </div>\n" + "</div>";
    public static String TRANSACTION_FEE_STRUCUTRE = "<div class=\"container\">\n" + "    <h3>Transaktionsgebühr berechnen</h3>\n" + "    <div class=\"alert alert-success\" role=\"alert\">\n" +
            "        Es konnte erfolgreich die Transaktionsgebühr für deine Eingaben berechnet werden.\n" + "    </div>\n" +
            "    Für die Transaktion der Art %s mit einem Betrag von %s fällt eine Transaktionsgebühr von %s an. Der Anteil der Transaktionsgebühren beträgt damit %s.\n" + "</div>";
    public static String TRANSACTION_REVERSE_STRUCTURE = "<div class=\"container\">\n" + "    <h3>Restbetrag berechnen</h3>\n" + "    <div class=\"alert alert-success\" role=\"alert\">\n" +
            "        Es konnte erfolgreich die Berechnung für den Restbetrag durchgeführt werden.\n" + "    </div>\n" +
            "    Damit nach Abzug der Transaktionsgebühren für eine Transaktion der Art %s ein Betrag von %s übrig bleibt, musst du dein Produkt/Dienstleistung für %s verkaufen. Der Anteil der Transaktionsgebühren beträgt damit %s.\n" +
            "</div>";
}
