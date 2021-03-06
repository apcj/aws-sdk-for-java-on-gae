/*
 * Copyright 2010-2011 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.autoscaling;

import com.amazonaws.*;
import com.amazonaws.services.autoscaling.model.*;


/**
 * Interface for accessing AmazonAutoScaling.
 * Auto Scaling <p>
 * Auto Scaling is a web service designed to automatically launch or
 * terminate EC2 instances based on user-defined policies, schedules, and
 * health checks. Auto Scaling responds automatically to changing
 * conditions. All you need to do is specify how it should respond to
 * those changes.
 * </p>
 * <p>
 * Auto Scaling groups can work across multiple Availability Zones -
 * distinct physical locations for the hosted Amazon EC2 instances - so
 * that if an Availability Zone becomes unavailable, Auto Scaling will
 * automatically redistribute applications to a different Availability
 * Zone.
 * </p>
 * <p>
 * Every API call returns a response meta data object that contains a
 * request identifier. Successful requests return an HTTP 200 status
 * code. Unsuccessful requests return an error object and an HTTP status
 * code of 400 or 500.
 * </p>
 * <p>
 * The current WSDL is available at:
 * </p>
 * <p>
 * <a
 * ef="http://autoscaling.amazonaws.com/doc/2010-08-01/AutoScaling.wsdl">
 * http://autoscaling.amazonaws.com/doc/2010-08-01/AutoScaling.wsdl </a>
 * </p>
 * <p>
 * <b>Endpoints</b>
 * </p>
 * <p>
 * Auto Scaling supports the following region-specific endpoints:
 * </p>
 * 
 * <ul>
 * <li>autoscaling.us-east-1.amazonaws.com</li>
 * <li>autoscaling.us-west-1.amazonaws.com</li>
 * <li>autoscaling.eu-west-1.amazonaws.com</li>
 * <li>autoscaling.ap-southeast-1.amazonaws.com</li>
 * 
 * </ul>
 */
public interface AmazonAutoScaling {

    /**
     * Overrides the default endpoint for this client ("https://autoscaling.amazonaws.com").
     * Callers can use this method to control which AWS region they want to work with.
     * <p>
     * Callers can pass in just the endpoint (ex: "ec2.amazonaws.com") or a full
     * URL, including the protocol (ex: "https://ec2.amazonaws.com"). If the
     * protocol is not specified here, the default protocol from this client's
     * {@link ClientConfiguration} will be used, which by default is HTTPS.
     * <p>
     * For more information on using AWS regions with the AWS SDK for Java, and
     * a complete list of all available endpoints for all AWS services, see:
     * <a href="http://developer.amazonwebservices.com/connect/entry.jspa?externalID=3912">
     * http://developer.amazonwebservices.com/connect/entry.jspa?externalID=3912</a>
     *
     * @param endpoint
     *            The endpoint (ex: "ec2.amazonaws.com") or a full URL,
     *            including the protocol (ex: "https://ec2.amazonaws.com") of
     *            the region specific AWS endpoint this client will communicate
     *            with.
     *
     * @throws IllegalArgumentException
     *             If any problems are detected with the specified endpoint.
     */
    public void setEndpoint(String endpoint) throws java.lang.IllegalArgumentException;
    
    /**
     * <p>
     * Creates a scheduled scaling action for a Auto Scaling group. If you
     * leave a parameter unspecified, the corresponding value remains
     * unchanged in the affected Auto Scaling group.
     * </p>
     *
     * @param putScheduledUpdateGroupActionRequest Container for the
     *           necessary parameters to execute the PutScheduledUpdateGroupAction
     *           service method on AmazonAutoScaling.
     * 
     * @throws AlreadyExistsException
     * @throws LimitExceededException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void putScheduledUpdateGroupAction(PutScheduledUpdateGroupActionRequest putScheduledUpdateGroupActionRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Adjusts the desired size of the AutoScalingGroup by initiating
     * scaling activities. When reducing the size of the group, it is not
     * possible to define which EC2 instances will be terminated. This
     * applies to any auto-scaling decisions that might result in terminating
     * instances.
     * </p>
     * <p>
     * There are two common use cases for <code>SetDesiredCapacity</code> :
     * one for users of the Auto Scaling triggering system, and
     * another for developers who write their own triggering systems. Both
     * use cases relate to the concept of cooldown.
     * </p>
     * <p>
     * In the first case, if you use the Auto Scaling triggering system,
     * <code>SetDesiredCapacity</code> changes the size of your Auto Scaling
     * group without regard to the cooldown period. This could be useful, for
     * example, if Auto Scaling did something unexpected for some reason. If
     * your cooldown period is 10 minutes, Auto Scaling would normally reject
     * requests to change the size of the group for that entire 10 minute
     * period. The <code>SetDesiredCapacity</code> command allows you to
     * circumvent this restriction and change the size of the group before
     * the end of the cooldown period.
     * </p>
     * <p>
     * In the second case, if you write your own triggering system, you can
     * use <code>SetDesiredCapacity</code> to control the size of your Auto
     * Scaling group. If you want the same cooldown functionality that Auto
     * Scaling offers, you can configure <code>SetDesiredCapacity</code> to
     * honor cooldown by setting the <code>HonorCooldown</code> parameter to
     * <code>true</code> .
     * 
     * </p>
     *
     * @param setDesiredCapacityRequest Container for the necessary
     *           parameters to execute the SetDesiredCapacity service method on
     *           AmazonAutoScaling.
     * 
     * @throws ScalingActivityInProgressException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void setDesiredCapacity(SetDesiredCapacityRequest setDesiredCapacityRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Deletes a policy created by PutScalingPolicy
     * </p>
     *
     * @param deletePolicyRequest Container for the necessary parameters to
     *           execute the DeletePolicy service method on AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void deletePolicy(DeletePolicyRequest deletePolicyRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Deletes a scheduled action previously created using the
     * PutScheduledUpdateGroupAction.
     * </p>
     *
     * @param deleteScheduledActionRequest Container for the necessary
     *           parameters to execute the DeleteScheduledAction service method on
     *           AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void deleteScheduledAction(DeleteScheduledActionRequest deleteScheduledActionRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Returns a full description of the launch configurations given the
     * specified names.
     * </p>
     * <p>
     * If no names are specified, then the full details of all launch
     * configurations are returned.
     * </p>
     *
     * @param describeLaunchConfigurationsRequest Container for the necessary
     *           parameters to execute the DescribeLaunchConfigurations service method
     *           on AmazonAutoScaling.
     * 
     * @return The response from the DescribeLaunchConfigurations service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeLaunchConfigurationsResult describeLaunchConfigurations(DescribeLaunchConfigurationsRequest describeLaunchConfigurationsRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Returns scaling process types for use in the ResumeProcesses and
     * SuspendProcesses actions.
     * </p>
     *
     * @param describeScalingProcessTypesRequest Container for the necessary
     *           parameters to execute the DescribeScalingProcessTypes service method
     *           on AmazonAutoScaling.
     * 
     * @return The response from the DescribeScalingProcessTypes service
     *         method, as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScalingProcessTypesResult describeScalingProcessTypes(DescribeScalingProcessTypesRequest describeScalingProcessTypesRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Returns a full description of each Auto Scaling group in the given
     * list. This includes all Amazon EC2 instances that are members of the
     * group. If a list of names is not provided, the service returns the
     * full details of all Auto Scaling groups.
     * </p>
     * <p>
     * This action supports pagination by returning a token if there are
     * more pages to retrieve. To get the next page, call this action again
     * with the returned token as the NextToken parameter.
     * </p>
     *
     * @param describeAutoScalingGroupsRequest Container for the necessary
     *           parameters to execute the DescribeAutoScalingGroups service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the DescribeAutoScalingGroups service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAutoScalingGroupsResult describeAutoScalingGroups(DescribeAutoScalingGroupsRequest describeAutoScalingGroupsRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Enables monitoring of group metrics for the Auto Scaling group
     * specified in AutoScalingGroupName. You can specify the list of enabled
     * metrics with the Metrics parameter.
     * </p>
     * <p>
     * Auto scaling metrics collection can be turned on only if the
     * <code>InstanceMonitoring.Enabled</code> flag, in the Auto Scaling
     * group's launch configuration, is set to <code>true</code> .
     * 
     * </p>
     *
     * @param enableMetricsCollectionRequest Container for the necessary
     *           parameters to execute the EnableMetricsCollection service method on
     *           AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void enableMetricsCollection(EnableMetricsCollectionRequest enableMetricsCollectionRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Terminates the specified instance. Optionally, the desired group size
     * can be adjusted.
     * </p>
     * <p>
     * <b>NOTE:</b> This call simply registers a termination request. The
     * termination of the instance cannot happen immediately.
     * </p>
     *
     * @param terminateInstanceInAutoScalingGroupRequest Container for the
     *           necessary parameters to execute the
     *           TerminateInstanceInAutoScalingGroup service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the TerminateInstanceInAutoScalingGroup
     *         service method, as returned by AmazonAutoScaling.
     * 
     * @throws ScalingActivityInProgressException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public TerminateInstanceInAutoScalingGroupResult terminateInstanceInAutoScalingGroup(TerminateInstanceInAutoScalingGroupRequest terminateInstanceInAutoScalingGroupRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Returns the scaling activities for the specified Auto Scaling group.
     * </p>
     * <p>
     * If the specified <i>ActivityIds</i> list is empty, all the activities
     * from the past six weeks are returned. Activities are sorted by
     * completion time. Activities still in progress appear first on the
     * list.
     * </p>
     * <p>
     * This action supports pagination. If the response includes a token,
     * there are more records available. To get the additional records,
     * repeat the request with the response token as the NextToken parameter.
     * </p>
     *
     * @param describeScalingActivitiesRequest Container for the necessary
     *           parameters to execute the DescribeScalingActivities service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the DescribeScalingActivities service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScalingActivitiesResult describeScalingActivities(DescribeScalingActivitiesRequest describeScalingActivitiesRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Runs the policy you create for your Auto Scaling group in
     * PutScalingPolicy.
     * </p>
     *
     * @param executePolicyRequest Container for the necessary parameters to
     *           execute the ExecutePolicy service method on AmazonAutoScaling.
     * 
     * @throws ScalingActivityInProgressException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void executePolicy(ExecutePolicyRequest executePolicyRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Returns a list of metrics and a corresponding list of granularities
     * for each metric.
     * </p>
     *
     * @param describeMetricCollectionTypesRequest Container for the
     *           necessary parameters to execute the DescribeMetricCollectionTypes
     *           service method on AmazonAutoScaling.
     * 
     * @return The response from the DescribeMetricCollectionTypes service
     *         method, as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeMetricCollectionTypesResult describeMetricCollectionTypes(DescribeMetricCollectionTypesRequest describeMetricCollectionTypesRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Returns descriptions of what each policy does. This action supports
     * pagination. If the response includes a token, there are more records
     * available. To get the additional records, repeat the request with the
     * response token as the NextToken parameter.
     * </p>
     *
     * @param describePoliciesRequest Container for the necessary parameters
     *           to execute the DescribePolicies service method on AmazonAutoScaling.
     * 
     * @return The response from the DescribePolicies service method, as
     *         returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribePoliciesResult describePolicies(DescribePoliciesRequest describePoliciesRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Returns policy adjustment types for use in the PutScalingPolicy
     * action.
     * </p>
     *
     * @param describeAdjustmentTypesRequest Container for the necessary
     *           parameters to execute the DescribeAdjustmentTypes service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the DescribeAdjustmentTypes service method,
     *         as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAdjustmentTypesResult describeAdjustmentTypes(DescribeAdjustmentTypesRequest describeAdjustmentTypesRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Deletes the specified auto scaling group if the group has no
     * instances and no scaling activities in progress.
     * </p>
     * <p>
     * <b>NOTE:</b> To remove all instances before calling
     * DeleteAutoScalingGroup, you can call UpdateAutoScalingGroup to set the
     * minimum and maximum size of the AutoScalingGroup to zero.
     * </p>
     *
     * @param deleteAutoScalingGroupRequest Container for the necessary
     *           parameters to execute the DeleteAutoScalingGroup service method on
     *           AmazonAutoScaling.
     * 
     * @throws ResourceInUseException
     * @throws ScalingActivityInProgressException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void deleteAutoScalingGroup(DeleteAutoScalingGroupRequest deleteAutoScalingGroupRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Creates a new Auto Scaling group with the specified name. Once the
     * creation request is completed, the AutoScalingGroup is ready to be
     * used in other calls.
     * </p>
     * <p>
     * <b>NOTE:</b> The Auto Scaling group name must be unique within the
     * scope of your AWS account, and under the quota of Auto Scaling groups
     * allowed for your account.
     * </p>
     *
     * @param createAutoScalingGroupRequest Container for the necessary
     *           parameters to execute the CreateAutoScalingGroup service method on
     *           AmazonAutoScaling.
     * 
     * @throws AlreadyExistsException
     * @throws LimitExceededException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void createAutoScalingGroup(CreateAutoScalingGroupRequest createAutoScalingGroupRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Returns a description of each Auto Scaling instance in the
     * InstanceIds list. If a list is not provided, the service returns the
     * full details of all instances up to a maximum of fifty.
     * </p>
     * <p>
     * This action supports pagination by returning a token if there are
     * more pages to retrieve. To get the next page, call this action again
     * with the returned token as the NextToken parameter.
     * </p>
     *
     * @param describeAutoScalingInstancesRequest Container for the necessary
     *           parameters to execute the DescribeAutoScalingInstances service method
     *           on AmazonAutoScaling.
     * 
     * @return The response from the DescribeAutoScalingInstances service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAutoScalingInstancesResult describeAutoScalingInstances(DescribeAutoScalingInstancesRequest describeAutoScalingInstancesRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Deletes the specified LaunchConfiguration.
     * </p>
     * <p>
     * The specified launch configuration must not be attached to an Auto
     * Scaling group. Once this call completes, the launch configuration is
     * no longer available for use.
     * </p>
     *
     * @param deleteLaunchConfigurationRequest Container for the necessary
     *           parameters to execute the DeleteLaunchConfiguration service method on
     *           AmazonAutoScaling.
     * 
     * @throws ResourceInUseException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void deleteLaunchConfiguration(DeleteLaunchConfigurationRequest deleteLaunchConfigurationRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Creates or updates a policy for an Auto Scaling group. To update an
     * existing policy, use the existing policy name and set the parameter(s)
     * you want to change. Any existing parameter not changed in an update to
     * an existing policy is not changed in this update request.
     * </p>
     *
     * @param putScalingPolicyRequest Container for the necessary parameters
     *           to execute the PutScalingPolicy service method on AmazonAutoScaling.
     * 
     * @return The response from the PutScalingPolicy service method, as
     *         returned by AmazonAutoScaling.
     * 
     * @throws LimitExceededException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public PutScalingPolicyResult putScalingPolicy(PutScalingPolicyRequest putScalingPolicyRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Sets the health status of an instance.
     * </p>
     *
     * @param setInstanceHealthRequest Container for the necessary parameters
     *           to execute the SetInstanceHealth service method on AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void setInstanceHealth(SetInstanceHealthRequest setInstanceHealthRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Updates the configuration for the specified AutoScalingGroup.
     * </p>
     * <p>
     * <b>NOTE:</b> To update an Auto Scaling group with a launch
     * configuration that has the InstanceMonitoring.enabled flag set to
     * false, you must first ensure that collection of group metrics is
     * disabled. Otherwise, calls to UpdateAutoScalingGroup will fail. If you
     * have previously enabled group metrics collection, you can disable
     * collection of all group metrics by calling DisableMetricsCollection.
     * </p>
     * <p>
     * The new settings are registered upon the completion of this call. Any
     * launch configuration settings take effect on any triggers after this
     * call returns. Triggers that are currently in progress aren't affected.
     * </p>
     * <p>
     * <b>NOTE:</b> If the new values are specified for the MinSize or
     * MaxSize parameters, then there will be an implicit call to
     * SetDesiredCapacity to set the group to the new MaxSize. All optional
     * parameters are left unchanged if not passed in the request.
     * </p>
     *
     * @param updateAutoScalingGroupRequest Container for the necessary
     *           parameters to execute the UpdateAutoScalingGroup service method on
     *           AmazonAutoScaling.
     * 
     * @throws ScalingActivityInProgressException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void updateAutoScalingGroup(UpdateAutoScalingGroupRequest updateAutoScalingGroupRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Lists all the actions scheduled for your Auto Scaling group that
     * haven't been executed. To see a list of action already executed, see
     * the activity record returned in DescribeScalingActivities.
     * </p>
     *
     * @param describeScheduledActionsRequest Container for the necessary
     *           parameters to execute the DescribeScheduledActions service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the DescribeScheduledActions service method,
     *         as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScheduledActionsResult describeScheduledActions(DescribeScheduledActionsRequest describeScheduledActionsRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Suspends Auto Scaling processes for an Auto Scaling group. To suspend
     * specific process types, specify them by name with the
     * <code>ScalingProcesses.member.N</code> parameter. To suspend all
     * process types, omit the <code>ScalingProcesses.member.N</code>
     * parameter.
     * </p>
     * <p>
     * <b>IMPORTANT:</b> Suspending either of the two primary process types,
     * Launch or Terminate, can prevent other process types from functioning
     * properly. For more information about processes and their dependencies,
     * see ProcessType.
     * </p>
     * <p>
     * To resume processes that have been suspended, use ResumeProcesses.
     * </p>
     *
     * @param suspendProcessesRequest Container for the necessary parameters
     *           to execute the SuspendProcesses service method on AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void suspendProcesses(SuspendProcessesRequest suspendProcessesRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Resumes Auto Scaling processes for an Auto Scaling group. For more
     * information, see SuspendProcesses and ProcessType.
     * </p>
     *
     * @param resumeProcessesRequest Container for the necessary parameters
     *           to execute the ResumeProcesses service method on AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void resumeProcesses(ResumeProcessesRequest resumeProcessesRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Creates a new launch configuration. Once created, the new launch
     * configuration is available for immediate use.
     * </p>
     * <p>
     * <b>NOTE:</b> The launch configuration name used must be unique, within
     * the scope of the client's AWS account, and the maximum limit of launch
     * configurations must not yet have been met, or else the call will fail.
     * </p>
     *
     * @param createLaunchConfigurationRequest Container for the necessary
     *           parameters to execute the CreateLaunchConfiguration service method on
     *           AmazonAutoScaling.
     * 
     * @throws AlreadyExistsException
     * @throws LimitExceededException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void createLaunchConfiguration(CreateLaunchConfigurationRequest createLaunchConfigurationRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Disables monitoring of group metrics for the Auto Scaling group
     * specified in AutoScalingGroupName. You can specify the list of
     * affected metrics with the Metrics parameter.
     * </p>
     *
     * @param disableMetricsCollectionRequest Container for the necessary
     *           parameters to execute the DisableMetricsCollection service method on
     *           AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void disableMetricsCollection(DisableMetricsCollectionRequest disableMetricsCollectionRequest) 
            throws AmazonServiceException, AmazonClientException;

    /**
     * <p>
     * Returns a full description of the launch configurations given the
     * specified names.
     * </p>
     * <p>
     * If no names are specified, then the full details of all launch
     * configurations are returned.
     * </p>
     * 
     * @return The response from the DescribeLaunchConfigurations service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeLaunchConfigurationsResult describeLaunchConfigurations() throws AmazonServiceException, AmazonClientException;
    
    /**
     * <p>
     * Returns scaling process types for use in the ResumeProcesses and
     * SuspendProcesses actions.
     * </p>
     * 
     * @return The response from the DescribeScalingProcessTypes service
     *         method, as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScalingProcessTypesResult describeScalingProcessTypes() throws AmazonServiceException, AmazonClientException;
    
    /**
     * <p>
     * Returns a full description of each Auto Scaling group in the given
     * list. This includes all Amazon EC2 instances that are members of the
     * group. If a list of names is not provided, the service returns the
     * full details of all Auto Scaling groups.
     * </p>
     * <p>
     * This action supports pagination by returning a token if there are
     * more pages to retrieve. To get the next page, call this action again
     * with the returned token as the NextToken parameter.
     * </p>
     * 
     * @return The response from the DescribeAutoScalingGroups service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAutoScalingGroupsResult describeAutoScalingGroups() throws AmazonServiceException, AmazonClientException;
    
    /**
     * <p>
     * Returns the scaling activities for the specified Auto Scaling group.
     * </p>
     * <p>
     * If the specified <i>ActivityIds</i> list is empty, all the activities
     * from the past six weeks are returned. Activities are sorted by
     * completion time. Activities still in progress appear first on the
     * list.
     * </p>
     * <p>
     * This action supports pagination. If the response includes a token,
     * there are more records available. To get the additional records,
     * repeat the request with the response token as the NextToken parameter.
     * </p>
     * 
     * @return The response from the DescribeScalingActivities service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScalingActivitiesResult describeScalingActivities() throws AmazonServiceException, AmazonClientException;
    
    /**
     * <p>
     * Returns a list of metrics and a corresponding list of granularities
     * for each metric.
     * </p>
     * 
     * @return The response from the DescribeMetricCollectionTypes service
     *         method, as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeMetricCollectionTypesResult describeMetricCollectionTypes() throws AmazonServiceException, AmazonClientException;
    
    /**
     * <p>
     * Returns descriptions of what each policy does. This action supports
     * pagination. If the response includes a token, there are more records
     * available. To get the additional records, repeat the request with the
     * response token as the NextToken parameter.
     * </p>
     * 
     * @return The response from the DescribePolicies service method, as
     *         returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribePoliciesResult describePolicies() throws AmazonServiceException, AmazonClientException;
    
    /**
     * <p>
     * Returns policy adjustment types for use in the PutScalingPolicy
     * action.
     * </p>
     * 
     * @return The response from the DescribeAdjustmentTypes service method,
     *         as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAdjustmentTypesResult describeAdjustmentTypes() throws AmazonServiceException, AmazonClientException;
    
    /**
     * <p>
     * Returns a description of each Auto Scaling instance in the
     * InstanceIds list. If a list is not provided, the service returns the
     * full details of all instances up to a maximum of fifty.
     * </p>
     * <p>
     * This action supports pagination by returning a token if there are
     * more pages to retrieve. To get the next page, call this action again
     * with the returned token as the NextToken parameter.
     * </p>
     * 
     * @return The response from the DescribeAutoScalingInstances service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAutoScalingInstancesResult describeAutoScalingInstances() throws AmazonServiceException, AmazonClientException;
    
    /**
     * <p>
     * Lists all the actions scheduled for your Auto Scaling group that
     * haven't been executed. To see a list of action already executed, see
     * the activity record returned in DescribeScalingActivities.
     * </p>
     * 
     * @return The response from the DescribeScheduledActions service method,
     *         as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScheduledActionsResult describeScheduledActions() throws AmazonServiceException, AmazonClientException;
    
    /**
     * Shuts down this client object, releasing any resources that might be held
     * open. This is an optional method, and callers are not expected to call
     * it, but can if they want to explicitly release any open resources. Once a
     * client has been shutdown, it should not be used to make any more
     * requests.
     */
    public void shutdown();
    
    /**
     * Returns additional metadata for a previously executed successful request, typically used for
     * debugging issues where a service isn't acting as expected.  This data isn't considered part
     * of the result data returned by an operation, so it's available through this separate,
     * diagnostic interface.
     * <p>
     * Response metadata is only cached for a limited period of time, so if you need to access
     * this extra diagnostic information for an executed request, you should use this method
     * to retrieve it as soon as possible after executing a request.
     *
     * @param request
     *            The originally executed request.
     *
     * @return The response metadata for the specified request, or null if none
     *         is available.
     */
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest request);

}
        