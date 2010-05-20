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
package com.amazonaws.services.ec2.model;
import com.amazonaws.AmazonWebServiceRequest;

/**
 * <p>
 * A request to register an AMI with Amazon EC2. Images must be
 * registered before they can be launched. To launch instances, use the
 * RunInstances operation.
 * </p>
 * <p>
 * Each AMI is associated with an unique ID which is provided by the
 * Amazon EC2 service through this operation. If needed, you can
 * deregister an AMI at any time.
 * </p>
 * <p>
 * <b>NOTE:</b> AMIs backed by Amazon EBS are automatically registered
 * when you create the image. However, you can use this to register a
 * snapshot of an instance backed by Amazon EBS. Amazon EBS snapshots are
 * not guaranteed to be bootable. For information on creating AMIs backed
 * by Amazon EBS, go to the Amazon Elastic Compute Cloud Developer Guide
 * or Amazon Elastic Compute Cloud User Guide.
 * </p>
 * <p>
 * Any modifications to an AMI backed by Amazon S3 invalidates this
 * registration. If you make changes to an image, deregister the previous
 * image and register the new image.
 * </p>
 */
public class RegisterImageRequest extends AmazonWebServiceRequest {

    /**
     * The full path to your AMI manifest in Amazon S3 storage.
     */
    private String imageLocation;

    /**
     * The name to give the new Amazon Machine Image. <p> Constraints: 3-128
     * alphanumeric characters, parenthesis (()), commas (,), slashes (/),
     * dashes (-), or underscores(_)
     */
    private String name;

    /**
     * The description describing the new AMI.
     */
    private String description;

    /**
     * The architecture of the image. <p> Valid Values: i386, x86_64
     */
    private String architecture;

    /**
     * The optional ID of a specific kernel to register with the new AMI.
     */
    private String kernelId;

    /**
     * The optional ID of a specific ramdisk to register with the new AMI.
     * <p> Some kernels require additional drivers at launch. Check the
     * kernel requirements for information on whether you need to specify a
     * RAM disk.
     */
    private String ramdiskId;

    /**
     * The root device name (e.g., /dev/sda1).
     */
    private String rootDeviceName;

    /**
     * The block device mappings for the new AMI, which specify how different
     * block devices (ex: EBS volumes and ephemeral drives) will be exposed
     * on instances launched from the new image.
     */
    private java.util.List<BlockDeviceMapping> blockDeviceMappings;

    /**
     * The full path to your AMI manifest in Amazon S3 storage.
     *
     * @return The full path to your AMI manifest in Amazon S3 storage.
     */
    public String getImageLocation() {
        return imageLocation;
    }
    
    /**
     * The full path to your AMI manifest in Amazon S3 storage.
     *
     * @param imageLocation The full path to your AMI manifest in Amazon S3 storage.
     */
    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }
    
    /**
     * The full path to your AMI manifest in Amazon S3 storage.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param imageLocation The full path to your AMI manifest in Amazon S3 storage.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RegisterImageRequest withImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
        return this;
    }
    
    
    /**
     * The name to give the new Amazon Machine Image. <p> Constraints: 3-128
     * alphanumeric characters, parenthesis (()), commas (,), slashes (/),
     * dashes (-), or underscores(_)
     *
     * @return The name to give the new Amazon Machine Image. <p> Constraints: 3-128
     *         alphanumeric characters, parenthesis (()), commas (,), slashes (/),
     *         dashes (-), or underscores(_)
     */
    public String getName() {
        return name;
    }
    
    /**
     * The name to give the new Amazon Machine Image. <p> Constraints: 3-128
     * alphanumeric characters, parenthesis (()), commas (,), slashes (/),
     * dashes (-), or underscores(_)
     *
     * @param name The name to give the new Amazon Machine Image. <p> Constraints: 3-128
     *         alphanumeric characters, parenthesis (()), commas (,), slashes (/),
     *         dashes (-), or underscores(_)
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * The name to give the new Amazon Machine Image. <p> Constraints: 3-128
     * alphanumeric characters, parenthesis (()), commas (,), slashes (/),
     * dashes (-), or underscores(_)
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param name The name to give the new Amazon Machine Image. <p> Constraints: 3-128
     *         alphanumeric characters, parenthesis (()), commas (,), slashes (/),
     *         dashes (-), or underscores(_)
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RegisterImageRequest withName(String name) {
        this.name = name;
        return this;
    }
    
    
    /**
     * The description describing the new AMI.
     *
     * @return The description describing the new AMI.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * The description describing the new AMI.
     *
     * @param description The description describing the new AMI.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * The description describing the new AMI.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param description The description describing the new AMI.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RegisterImageRequest withDescription(String description) {
        this.description = description;
        return this;
    }
    
    
    /**
     * The architecture of the image. <p> Valid Values: i386, x86_64
     *
     * @return The architecture of the image. <p> Valid Values: i386, x86_64
     */
    public String getArchitecture() {
        return architecture;
    }
    
    /**
     * The architecture of the image. <p> Valid Values: i386, x86_64
     *
     * @param architecture The architecture of the image. <p> Valid Values: i386, x86_64
     */
    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }
    
    /**
     * The architecture of the image. <p> Valid Values: i386, x86_64
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param architecture The architecture of the image. <p> Valid Values: i386, x86_64
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RegisterImageRequest withArchitecture(String architecture) {
        this.architecture = architecture;
        return this;
    }
    
    
    /**
     * The optional ID of a specific kernel to register with the new AMI.
     *
     * @return The optional ID of a specific kernel to register with the new AMI.
     */
    public String getKernelId() {
        return kernelId;
    }
    
    /**
     * The optional ID of a specific kernel to register with the new AMI.
     *
     * @param kernelId The optional ID of a specific kernel to register with the new AMI.
     */
    public void setKernelId(String kernelId) {
        this.kernelId = kernelId;
    }
    
    /**
     * The optional ID of a specific kernel to register with the new AMI.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param kernelId The optional ID of a specific kernel to register with the new AMI.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RegisterImageRequest withKernelId(String kernelId) {
        this.kernelId = kernelId;
        return this;
    }
    
    
    /**
     * The optional ID of a specific ramdisk to register with the new AMI.
     * <p> Some kernels require additional drivers at launch. Check the
     * kernel requirements for information on whether you need to specify a
     * RAM disk.
     *
     * @return The optional ID of a specific ramdisk to register with the new AMI.
     *         <p> Some kernels require additional drivers at launch. Check the
     *         kernel requirements for information on whether you need to specify a
     *         RAM disk.
     */
    public String getRamdiskId() {
        return ramdiskId;
    }
    
    /**
     * The optional ID of a specific ramdisk to register with the new AMI.
     * <p> Some kernels require additional drivers at launch. Check the
     * kernel requirements for information on whether you need to specify a
     * RAM disk.
     *
     * @param ramdiskId The optional ID of a specific ramdisk to register with the new AMI.
     *         <p> Some kernels require additional drivers at launch. Check the
     *         kernel requirements for information on whether you need to specify a
     *         RAM disk.
     */
    public void setRamdiskId(String ramdiskId) {
        this.ramdiskId = ramdiskId;
    }
    
    /**
     * The optional ID of a specific ramdisk to register with the new AMI.
     * <p> Some kernels require additional drivers at launch. Check the
     * kernel requirements for information on whether you need to specify a
     * RAM disk.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param ramdiskId The optional ID of a specific ramdisk to register with the new AMI.
     *         <p> Some kernels require additional drivers at launch. Check the
     *         kernel requirements for information on whether you need to specify a
     *         RAM disk.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RegisterImageRequest withRamdiskId(String ramdiskId) {
        this.ramdiskId = ramdiskId;
        return this;
    }
    
    
    /**
     * The root device name (e.g., /dev/sda1).
     *
     * @return The root device name (e.g., /dev/sda1).
     */
    public String getRootDeviceName() {
        return rootDeviceName;
    }
    
    /**
     * The root device name (e.g., /dev/sda1).
     *
     * @param rootDeviceName The root device name (e.g., /dev/sda1).
     */
    public void setRootDeviceName(String rootDeviceName) {
        this.rootDeviceName = rootDeviceName;
    }
    
    /**
     * The root device name (e.g., /dev/sda1).
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param rootDeviceName The root device name (e.g., /dev/sda1).
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RegisterImageRequest withRootDeviceName(String rootDeviceName) {
        this.rootDeviceName = rootDeviceName;
        return this;
    }
    
    
    /**
     * The block device mappings for the new AMI, which specify how different
     * block devices (ex: EBS volumes and ephemeral drives) will be exposed
     * on instances launched from the new image.
     *
     * @return The block device mappings for the new AMI, which specify how different
     *         block devices (ex: EBS volumes and ephemeral drives) will be exposed
     *         on instances launched from the new image.
     */
    public java.util.List<BlockDeviceMapping> getBlockDeviceMappings() {
        if (blockDeviceMappings == null) {
            blockDeviceMappings = new java.util.ArrayList<BlockDeviceMapping>();
        }
        return blockDeviceMappings;
    }
    
    /**
     * The block device mappings for the new AMI, which specify how different
     * block devices (ex: EBS volumes and ephemeral drives) will be exposed
     * on instances launched from the new image.
     *
     * @param blockDeviceMappings The block device mappings for the new AMI, which specify how different
     *         block devices (ex: EBS volumes and ephemeral drives) will be exposed
     *         on instances launched from the new image.
     */
    public void setBlockDeviceMappings(java.util.Collection<BlockDeviceMapping> blockDeviceMappings) {
        java.util.List<BlockDeviceMapping> blockDeviceMappingsCopy = new java.util.ArrayList<BlockDeviceMapping>();
        if (blockDeviceMappings != null) {
            blockDeviceMappingsCopy.addAll(blockDeviceMappings);
        }
        this.blockDeviceMappings = blockDeviceMappingsCopy;
    }
    
    /**
     * The block device mappings for the new AMI, which specify how different
     * block devices (ex: EBS volumes and ephemeral drives) will be exposed
     * on instances launched from the new image.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param blockDeviceMappings The block device mappings for the new AMI, which specify how different
     *         block devices (ex: EBS volumes and ephemeral drives) will be exposed
     *         on instances launched from the new image.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RegisterImageRequest withBlockDeviceMappings(BlockDeviceMapping... blockDeviceMappings) {
        for (BlockDeviceMapping value : blockDeviceMappings) {
            getBlockDeviceMappings().add(value);
        }
        return this;
    }
    
    /**
     * The block device mappings for the new AMI, which specify how different
     * block devices (ex: EBS volumes and ephemeral drives) will be exposed
     * on instances launched from the new image.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param blockDeviceMappings The block device mappings for the new AMI, which specify how different
     *         block devices (ex: EBS volumes and ephemeral drives) will be exposed
     *         on instances launched from the new image.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RegisterImageRequest withBlockDeviceMappings(java.util.Collection<BlockDeviceMapping> blockDeviceMappings) {
        java.util.List<BlockDeviceMapping> blockDeviceMappingsCopy = new java.util.ArrayList<BlockDeviceMapping>();
        if (blockDeviceMappings != null) {
            blockDeviceMappingsCopy.addAll(blockDeviceMappings);
        }
        this.blockDeviceMappings = blockDeviceMappingsCopy;

        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("ImageLocation: " + imageLocation + ", ");
        sb.append("Name: " + name + ", ");
        sb.append("Description: " + description + ", ");
        sb.append("Architecture: " + architecture + ", ");
        sb.append("KernelId: " + kernelId + ", ");
        sb.append("RamdiskId: " + ramdiskId + ", ");
        sb.append("RootDeviceName: " + rootDeviceName + ", ");
        sb.append("BlockDeviceMappings: " + blockDeviceMappings + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    