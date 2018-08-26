package com.github.fedelodo.serialjob2libjava;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpObj {

    public String SerialJob2DecodeChar(String strBuffer) {
        char ch1 = (char) 1;
        strBuffer = strBuffer.replace(String.valueOf(ch1), "<SOH>");
        char ch2 = (char) 2;
        strBuffer = strBuffer.replace(String.valueOf(ch2), "<STX>");
        char ch3 = (char) 3;
        strBuffer = strBuffer.replace(String.valueOf(ch3), "<ETX>");
        char ch4 = (char) 4;
        strBuffer = strBuffer.replace(String.valueOf(ch4), "<EOT>");
        char ch5 = (char) 21;
        strBuffer = strBuffer.replace(String.valueOf(ch5), "<NAK>");
        char ch6 = (char) 6;
        strBuffer = strBuffer.replace(String.valueOf(ch6), "<ACK>");
        return strBuffer;
    }

    private String Sj2Command(Command command, Socket connection) {
        DataOutputStream out;
        try {
            out = new DataOutputStream(connection.getOutputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            out.writeBytes(command.toString());
            out.flush();
            String response = reader.readLine();
            reader.close();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Invalid Command";
    }

    private String getSj2Response(String response) {
        if (response.charAt(1) == (char) 6) {
            return "Command Executed, checksum: " + response.substring(2, response.length() - 1);
        } else if (response.charAt(1) == (char) 21) {
            return "Command Failed, error:" + response.substring(2, response.length() - 1);
        } else {
            return "Invalid response";
        }
    }


    public String getLaserStatus(Socket connection) {
        Command cmd = new Command("K");
        String response = Sj2Command(cmd, connection);
        String message = response.substring(2, (response.length() - 1));
        switch (Integer.parseInt(message)) {
            case (-1):
                throw new SerialJob2Exception("Fly Cad No Init");
            case (0):
                return "FlyIModeNone";
            case (1):
                return "FlyImodeEdit";
            case (2):
                return "FlyImodeRun";
            case (3):
                return "FlyImodeRunRequest";
            case (4):
                return "FlyImodeEngrave";
            case (5):
                return "FlyImodePreview";
            case (6):
                return "FlyRJobLoading";
            case (7):
                return "FlyLaserFaultStatus";
            case (50):
                return "FlyRMoveMotors";
            case (51):
                return "FlyRStartMArk";
            case (52):
                return "FlyRStopCommand";
            case (53):
                return "FlyLMovingMotors";
            case (54):
                return "FlyLMovOK";
            case (55):
                return "FlyLMovERR";
            case (56):
                return "FlyPauseMark";
            default:
                return "Invalid Status";
        }
    }

    public String GetDynamicTextNum(Socket connection) {
        Command cmd = new Command("#D");
        String response = Sj2Command(cmd, connection);
        return getSj2Response(response);
    }


    public String ActivateFlyImodeRun(Socket connection) {
        Command cmd = new Command("XR");
        String response = Sj2Command(cmd, connection);
        return getSj2Response(response);
    }

    public String StartMarking(Socket connection) {
        String response2 = "";
        Command cmd = new Command("S");
        String response1 = Sj2Command(cmd, connection);
        System.out.println("Marker Started " + getSj2Response(response1));
        BufferedReader reader;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            response2 = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getSj2Response(response2);
    }

    public String StopAll(Socket connection) {
        Command cmd = new Command("HLT");
        String response = Sj2Command(cmd, connection);
        return getSj2Response(response);
    }

    public String BasicCommand(BasicCommand command, Socket connection) {
        Command cmd = new Command(command);
        String response = Sj2Command(cmd, connection);
        return getSj2Response(response);
    }

    public String SpecialCommand(SpecialCommand command, Socket connection) {
        Command cmd = new Command(command);
        String response = Sj2Command(cmd, connection);
        return getSj2Response(response);
    }


}





