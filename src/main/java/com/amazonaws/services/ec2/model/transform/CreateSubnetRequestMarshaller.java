/*
 * Copyright 2010 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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
package com.amazonaws.services.ec2.model.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.services.ec2.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/**
 * Create Subnet Request Marshaller
 */
public class CreateSubnetRequestMarshaller implements Marshaller<Request<CreateSubnetRequest>, CreateSubnetRequest> {

    public Request<CreateSubnetRequest> marshall(CreateSubnetRequest createSubnetRequest) {
        Request<CreateSubnetRequest> request = new DefaultRequest<CreateSubnetRequest>(createSubnetRequest, "AmazonEC2");
        request.addParameter("Action", "CreateSubnet");
        request.addParameter("Version", "2010-08-31");
        if (createSubnetRequest != null) {
            if (createSubnetRequest.getVpcId() != null) {
                request.addParameter("VpcId", StringUtils.fromString(createSubnetRequest.getVpcId()));
            }
        }
        if (createSubnetRequest != null) {
            if (createSubnetRequest.getCidrBlock() != null) {
                request.addParameter("CidrBlock", StringUtils.fromString(createSubnetRequest.getCidrBlock()));
            }
        }
        if (createSubnetRequest != null) {
            if (createSubnetRequest.getAvailabilityZone() != null) {
                request.addParameter("AvailabilityZone", StringUtils.fromString(createSubnetRequest.getAvailabilityZone()));
            }
        }


        return request;
    }
}
