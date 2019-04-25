package com.example.demo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.cloud.dialogflow.v2.TextInput.Builder;
import com.google.cloud.dialogflow.v2beta1.WebhookResponse;

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
   
   @RequestMapping(method = RequestMethod.POST, value = "/webhook")
//   public TestingResponse dialogFlowWebHookPOST(@RequestBody IntentRequest requestStr,HttpServletRequest servletRequest) throws IOException {
   public ResponseDailog dialogFlowWebHookPOST(@RequestBody IntentRequest requestStr,HttpServletRequest servletRequest) throws IOException {
//	   IntentResponse inRes =new IntentResponse();
//	   TestingResponse testing = new TestingResponse();
//	   testing.setSpeech("TESTING WORKS FINE POST response");
//	   return testing;
	   
	   
	   FulfillmentMessages fulfillmentMessages1 = new FulfillmentMessages();
	   
	   
	   Text txt = new Text();
	   txt.settext(Arrays.asList("alok testing"));

	   
	   fulfillmentMessages1.setText(txt);
	   
	   List <FulfillmentMessages> messages = new ArrayList();
	   messages.add(fulfillmentMessages1);
	   
	   ResponseDailog res = new ResponseDailog();
	   res.setFulfillmentText("fulfillmentText");
	   res.setSource("source");
	   res.setFulfillmentMessages(messages);
	   res.setSpeech("speech dsfsafdsf works fine");
	   return res;
	   
	   
	   
//       try {
//           String email = servletRequest.getHeader("username");
//           String password = servletRequest.getHeader("password");
//           this.userController.authenticate(new User(email,password,null,null));
//
//           GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
//           GoogleCloudDialogflowV2WebhookRequest request = jacksonFactory.createJsonParser(requestStr).parse(GoogleCloudDialogflowV2WebhookRequest.class);
//
//           Map<String,Object> params = request.getQueryResult().getParameters();
//           if (params.size() > 0) {
//               response.setFulfillmentText(dialogFlowController.processMessage(params));
//           }
//           else {
//               response.setFulfillmentText("Sorry you didn't send enough to process");
//           }
//
//           return new ResponseEntity<GoogleCloudDialogflowV2WebhookResponse>(response,HttpStatus.OK);
//       }
//       catch (Exception ex) {
//           return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
//       }

   }


   
   @RequestMapping(method = RequestMethod.GET, value = "/webhook")
   //TestingResponse
   public  ResponseDailog dialogFlowWebHookGET(@RequestBody IntentRequest requestStr,HttpServletRequest servletRequest) throws IOException {

//	   IntentResponse inRes =new IntentResponse();
//	   TestingResponse testing = new TestingResponse();
//	   testing.setSpeech("TESTING WORKS FINE GET response");
//	   return testing;

	   

	   FulfillmentMessages fulfillmentMessages1 = new FulfillmentMessages();
	   
	   
	   Text txt = new Text();
	   txt.settext(Arrays.asList("alok testing"));

	   
	   fulfillmentMessages1.setText(txt);
	   
	   List <FulfillmentMessages> messages = new ArrayList();
	   messages.add(fulfillmentMessages1);
	   
	   ResponseDailog res = new ResponseDailog();
	   res.setFulfillmentText("fulfillmentText");
	   res.setSource("source");
	   res.setFulfillmentMessages(messages);
	   res.setSpeech("speech dsfsafdsf works fine");
	   return res;
   
   }
}  
   class ResponseDailog {
	   private String fulfillmentText;
	   List <FulfillmentMessages> fulfillmentMessages;
	   private String source;
	   String speech;

	   public String getSpeech() {
	   	return speech;
	   }

	   public void setSpeech(String speech) {
	   	this.speech = speech;
	   }
	   
	   // Getter Methods 

	   public String getFulfillmentText() {
	    return fulfillmentText;
	   }

	   public String getSource() {
	    return source;
	   }

	   // Setter Methods 

	   public void setFulfillmentText(String fulfillmentText) {
	    this.fulfillmentText = fulfillmentText;
	   }

	   public void setSource(String source) {
	    this.source = source;
	   }
	   
	   public List<FulfillmentMessages> getFulfillmentMessages() {
		return fulfillmentMessages;
		}
	
		public void setFulfillmentMessages(List<FulfillmentMessages> fulfillmentMessages) {
			this.fulfillmentMessages = fulfillmentMessages;
		}
	
	  }
  
   class FulfillmentMessages{
	   Text text;

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
   }
   
   
   class Text{
	   List<String> text;

	public List<String> gettext() {
		return text;
	}

	public void settext(List<String> text) {
		this.text = text;
	}
   }


