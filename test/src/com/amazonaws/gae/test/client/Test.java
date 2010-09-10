package com.amazonaws.gae.test.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import com.google.gwt.user.client.ui.Grid;
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
	VerticalPanel dialogVPanel;
	Map<String, Grid> resultsGrids;
	volatile int testsReturned = 0;
	volatile int totalTests = 0;

	
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
		resultsGrids = new HashMap<String, Grid>();

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
		dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending keys to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Results:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
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
			dialogBox.center();
			
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
			totalTests += result.size();
			Grid resultsGrid = new Grid(result.size() + 2, 3);
			resultsGrid.setBorderWidth(1);
			resultsGrid.setText(0, 0, testSuiteName);
			resultsGrid.setText(1, 0, "Test");
			resultsGrid.setText(1, 1, "Result");
			resultsGrid.setText(1, 2, "Duration");
			resultsGrids.put(testSuiteName, resultsGrid);
			dialogVPanel.add(resultsGrid);

			testingService.runTests(testSuiteName, accessKeyField.getText(), secretKeyField.getText(), new RunTestsAsyncCallback());
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
			testsReturned += result.getNumberOfTests();
			Grid resultsGrid = resultsGrids.get(result.suite);
			Iterator<String> it = result.getTests().iterator();
			for (int i = 0; i < result.getNumberOfTests(); i++) {
				String test = it.next();
				resultsGrid.setText(i + 2, 0, test);
				resultsGrid.setText(i + 2, 1, result.getResult(test).code.toString());
				resultsGrid.setText(i + 2, 2, result.getResult(test).time + " ms.");
			}
			if (testsReturned == totalTests) {
				dialogVPanel.add(closeButton);
				closeButton.setFocus(true);
			}
		}
	}
}
