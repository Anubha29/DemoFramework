package util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class JiraLibrary {


    public static void addAttachmentToIssue(String user, String pass, String reportPath, String jiraId, String batchFilePath) {
        try{
        createAddAttachmentBat(user, pass, reportPath, jiraId);
        executeAttachmentBat(batchFilePath);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createAddAttachmentBat(String user, String pass, String filePath, String jiraID) throws Exception
    {
        String jiraURL="https://jira"+jiraID+"/attachments";
        File file=new File("attachFile.bat");
        String batchText = "curl -D- -u "+user+":"+pass+
                " -X POST -H \"X-Atlassian-Token: no-check\" -F \"file=@"+
                filePath+"\" "+jiraURL;
        FileOutputStream fos=new FileOutputStream(file);
        DataOutputStream dos=new DataOutputStream(fos);
        System.out.println("Creating batch file:\n"+batchText);
        dos.writeBytes(batchText);
    }

    public static void executeAttachmentBat(String batFileLocation) throws Exception
    {
        String cmd="cmd /c start "+batFileLocation;
        Runtime r=Runtime.getRuntime();
        Process pr=r.exec(cmd);
        Thread.sleep(2000);
    }

}
