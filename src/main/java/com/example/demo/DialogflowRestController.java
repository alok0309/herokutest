package com.example.demo;


import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.cloud.dialogflow.v2.TextInput.Builder;

@RestController
public class DialogflowRestController {

   private String userText = "hello";
   private final String LANG_CODE = "en-US";
   private final String PROJECT_ID = "mitelvicebottest14apr";
   private String sessionId = UUID.randomUUID().toString();
//   private final String credential = "dialogflow-akepbv@mitelvicebottest14apr.iam.gserviceaccount.com";
//   private final String URL = "https://dialogflow.googleapis.com/v2/{session=projects/MY_PROJECT_ID/agent/sessions/" +
//           sessionId + "}:detectIntent";

   @GetMapping("testing")
   public String tetsing() {
	   return "testing";
   }
   
   @RequestMapping("/intent")
   public String doThings() throws IOException {

       try (SessionsClient sessionsClient = SessionsClient.create()) {
           SessionName session = SessionName.of(PROJECT_ID, sessionId);

           Builder textInput = TextInput.newBuilder().setText(userText).setLanguageCode(LANG_CODE);

           QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

           DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
           return response.toString();
       }
   }
   
   @GetMapping("/webhook")
   public String webhookTesting() throws IOException {
	   System.out.println("webhook testing works");
	   return "webhook testing works";
   }
}
