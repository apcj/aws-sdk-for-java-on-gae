package com.amazonaws.gae.test.client;

import java.util.List;
import java.util.Set;

import com.amazonaws.gae.test.shared.FieldVerifier;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResult;
import com.amazonaws.gae.test.shared.awsunit.AWSTestResultSet;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Test implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Testing service.
	 */
	private final TestingServiceAsync testingService = GWT
			.create(TestingService.class);
	
	/**
	 * Create a remote service proxy to talk to the server-side TestListing service.
	 */
	private final TestListingServiceAsync testListingService = GWT
			.create(TestListingService.class);

	Button testButton;
	TextBox accessKeyField;
	TextBox secretKeyField;
	Label errorLabel;
	DialogBox dialogBox;
	Button closeButton;
	Label textToServerLabel;
	HTML serverResponseLabel;

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		testButton = new Button("Begin Testing");
		accessKeyField = new TextBox();
		accessKeyField.setText("Access Key");
		secretKeyField = new TextBox();
		secretKeyField.setText("Secret Key");
		errorLabel = new Label();

		// We can add style names to widgets
		testButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(accessKeyField);
		RootPanel.get("nameFieldContainer").add(secretKeyField);
		RootPanel.get("sendButtonContainer").add(testButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		accessKeyField.setFocus(true);
		accessKeyField.selectAll();

		// Create the popup dialog box
		dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		textToServerLabel = new Label();
		serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending keys to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Results:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				testButton.setEnabled(true);
				testButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendKeysToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendKeysToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendKeysToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String accessKey = accessKeyField.getText();
				String secretKey = secretKeyField.getText();
				if (!FieldVerifier.isValidAccessKey(accessKey)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				testButton.setEnabled(false);
				textToServerLabel.setText("Access Key: " + accessKey + " || " + "Secret Key: " + secretKey);
				serverResponseLabel.setText("");
				
				testListingService.listTestSuites(new ListTestSuitesAsyncCallback());
				/*
				testingService.runTests(accessKey, secretKey,
						new AsyncCallback<AWSTestResultSet>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(AWSTestResultSet result) {
								dialogBox.setText("Test Results");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML("Passed Tests: " + result.getNumberOfTests());
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
				*/
				
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		testButton.addClickHandler(handler);
		accessKeyField.addKeyUpHandler(handler);
	}
	
	class ListTestSuitesAsyncCallback implements AsyncCallback<Set<String>> {
		@Override
		public void onFailure(Throwable caught) {
			// Show the RPC error message to the user
			dialogBox.setText("Remote Procedure Call - Failure");
			serverResponseLabel.addStyleName("serverResponseLabelError");
			serverResponseLabel.setHTML(SERVER_ERROR);
			dialogBox.center();
			closeButton.setFocus(true);
		}

		@Override
		public void onSuccess(Set<String> result) {
			dialogBox.setText("Test Results:");
			serverResponseLabel.removeStyleName("serverResponseLabelError");
			String testSuiteList = "";
			for (String testSuiteName : result) {
				testSuiteList += testSuiteName + "<br>";
			}
			serverResponseLabel.setHTML(testSuiteList);
			dialogBox.center();
			closeButton.setFocus(true);			
			
			for (String testSuiteName : result) {
				testListingService.listTests(testSuiteName, new ListTestsAsyncCallback(testSuiteName));
			}
		}
	}
	
	class ListTestsAsyncCallback implements AsyncCallback<List<String>> {
		private String testSuiteName;
		public ListTestsAsyncCallback(String testSuiteName) {
			this.testSuiteName = testSuiteName;
		}
		@Override
		public void onFailure(Throwable caught) {
			// Show the RPC error message to the user
			dialogBox.setText("Remote Procedure Call - Failure");
			serverResponseLabel.addStyleName("serverResponseLabelError");
			serverResponseLabel.setHTML(SERVER_ERROR);
			dialogBox.center();
			closeButton.setFocus(true);
		}

		@Override
		public void onSuccess(List<String> result) {
			String testList = "";
			for (String testName : result) {
				testList += testName + "<br>";
			}
			serverResponseLabel.setHTML(serverResponseLabel.getHTML() + testList);
			
			for (String testName : result) {
				testingService.runTest(testSuiteName, testName, accessKeyField.getText(), secretKeyField.getText(), new RunTestAsyncCallback());
			}
		}
	}
	
	class RunTestAsyncCallback implements AsyncCallback<AWSTestResult> {

		@Override
		public void onFailure(Throwable caught) {
			// Show the RPC error message to the user
			dialogBox.setText("Remote Procedure Call - Failure");
			serverResponseLabel.addStyleName("serverResponseLabelError");
			serverResponseLabel.setHTML(SERVER_ERROR);
			dialogBox.center();
			closeButton.setFocus(true);
		}

		@Override
		public void onSuccess(AWSTestResult result) {
			serverResponseLabel.setHTML(serverResponseLabel.getHTML() + "SUCCESS<br>");
		}
		
	}
	
	class RunTestsAsyncCallback implements AsyncCallback<AWSTestResultSet> {
		@Override
		public void onFailure(Throwable caught) {
			// Show the RPC error message to the user
			dialogBox.setText("Remote Procedure Call - Failure");
			serverResponseLabel.addStyleName("serverResponseLabelError");
			serverResponseLabel.setHTML(SERVER_ERROR);
			dialogBox.center();
			closeButton.setFocus(true);
		}

		@Override
		public void onSuccess(AWSTestResultSet result) {
		}
	}
}
