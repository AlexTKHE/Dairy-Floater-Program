/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package dairyfloater;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import org.eclipse.jetty.client.api.Response;

import com.google.common.base.Utf8;

import spark.Spark;

record dataRetrival(String schedule, int lineInput, int startInput, int endInput, int cashiersInput,
        int orderTakersInput) {
};

public class App {

    public static String shorten(String n) {
        return n = n.substring(n.indexOf("~"));
    }

    public static String steal(String n) {
        return n.substring(0, n.indexOf("~"));
    }

    public static dataRetrival retrive(StringBuilder body) {
        String schedule = body.toString().substring(body.toString().indexOf("schedule=") + 9).trim();
        schedule = schedule.substring(schedule.indexOf("=\"schedule\"") + 11, schedule.indexOf("----"));
        // schedule = schedule.substring(schedule.length()-4) + " " +
        // schedule.substring(0, schedule.length()-4);

        String lineInputS = body.toString().substring(body.toString().indexOf("name=\"lineInput\"") + 9).trim();
        lineInputS = lineInputS.substring(lineInputS.indexOf("Input\"") + 6, lineInputS.indexOf("----"));
        int lineInput = Integer.parseInt(lineInputS);

        String startInputS = body.toString().substring(body.toString().indexOf("name=\"startInput\"") + 9).trim();
        startInputS = startInputS.substring(startInputS.indexOf("Input\"") + 6, startInputS.indexOf("----"));
        int startInput = Integer.parseInt(startInputS);

        String endInputS = body.toString().substring(body.toString().indexOf("name=\"endInput\"") + 9).trim();
        endInputS = endInputS.substring(endInputS.indexOf("Input\"") + 6, endInputS.indexOf("----"));
        int endInput = Integer.parseInt(endInputS);

        String cashiersInputS = body.toString().substring(body.toString().indexOf("name=\"cashiersInput\"") + 9).trim();
        cashiersInputS = cashiersInputS.substring(cashiersInputS.indexOf("Input\"") + 6,
                cashiersInputS.indexOf("----"));
        int cashiersInput = Integer.parseInt(cashiersInputS);

        String orderTakersS = body.toString().substring(body.toString().indexOf("name=\"orderTakersInput\"") + 9)
                .trim();
        orderTakersS = orderTakersS.substring(orderTakersS.indexOf("Input\"") + 6, orderTakersS.indexOf("----"));
        int orderTakersInput = Integer.parseInt(orderTakersS);

        dataRetrival data = new dataRetrival(schedule, lineInput, startInput, endInput, cashiersInput,
                orderTakersInput);
        return data;
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        HalfHourIntervals scheduleCreator = new HalfHourIntervals();
        ScanIn normalizeData = new ScanIn();

        // Randomize random = new Randomize();
        
        // Employee[] employees = random.createEmployees(34);
        
       // String s =scheduleCreator.createRotations(employees, 14, 21 ,3,2,3);
        //System.out.println("WORK" + " " + s);

        staticFiles.location("/public");
        port(8080);
        get("/test", (req, res) -> {
            res.redirect("index.html");
            return null;
        });

        Spark.post("/api/message", (req, res) -> {
            return "Hi, Kirk, this API call worked";
        });


        
        Spark.post("/number/employees", (req, res) -> {
            // Retrieve the value of 'n' from the request
           // int n = Integer.parseInt(req.queryParams("n"));

            String whenWorkSched = req.queryParams("n");
            
            String filePath = "/Users/alex/Desktop/Dairy-Floater-Program/app/src/main/resources/txt/test.txt";
            String type =  "UTF-8";
          
            PrintWriter writer = new PrintWriter(filePath, type);

            // whenWorkSched = whenWorkSched.replaceAll("\t{2,}", "\n");

            writer.println(whenWorkSched);

            writer.close();

            return scheduleCreator.createSchedule(filePath);
            // return "Alli, Station Manager, 6-9.";
        });

        Spark.post("/downloadFile", (req, res) -> {
            String filePath = "/Users/alex/Desktop/Dairy-Floater-Program/app/src/main/resources/txt/rotations.txt";
            try {
            
            res.header("Content-Disposition", "attachment; filename=rotations.txt");
            res.type("text/plain");

            return Files.newInputStream(Path.of(filePath));
            
            } catch (IOException e) {
                e.printStackTrace();
                res.status(500);
                return "Error generating the file.";
            }
        });

        Spark.post("/api/createRotations", (req, res) -> {

            try (InputStream inputStream = req.raw().getInputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder body = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    body.append(line);
                }

                // Now 'body.toString()' contains the raw body of the request
                dataRetrival data = retrive(body);

                String schedule = data.schedule();

                int linesInput = data.lineInput();
                int startInput = data.startInput();
                if (startInput < 9) {
                    startInput += 12;
                }
                int endInput = data.endInput();
                if (endInput < 10) {
                    endInput += 12;
                }
                int cashiersInput = data.cashiersInput();
                int orderTakersInput = data.orderTakersInput();

                try {

                    String schedule2 = "";
                    schedule2 = normalizeData.processString(schedule);
                    String[] employeesString = normalizeData.createArray(schedule2);
                    Employee[] employees = normalizeData.createEmployees(employeesString);

                      scheduleCreator.createRotations(employees, startInput, endInput,
                      linesInput, cashiersInput, orderTakersInput);
              
                    

                    String rotations = normalizeData.readInRotations(linesInput);
                    

                    

                    return (rotations);

                } catch (Exception e) {

                    e.printStackTrace();
                    return "Error processing employees";
                }

            } catch (IOException e) {

                e.printStackTrace();
                return "Error reading the request body";
            }

        });
    }
}
