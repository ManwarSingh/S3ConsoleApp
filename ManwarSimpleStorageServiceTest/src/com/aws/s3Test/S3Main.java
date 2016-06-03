package com.aws.s3Test;

import java.util.Scanner;

import org.springframework.test.annotation.SystemProfileValueSource;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class S3Main {

	public static void main(String[] args) {
		 AWSCredentials credentials = null;
	        try {
	            credentials = new ProfileCredentialsProvider("MannRawat").getCredentials();
	        } catch (Exception e) {
	            throw new AmazonClientException(
	                    "Cannot load the credentials from the credential profiles file. " +
	                    "Please make sure that your credentials file is at the correct " +
	                    "location (C:\\Users\\Administrator\\.aws\\credentials), and is in valid format.",
	                    e);
	        }
	        Scanner scanner = new Scanner(System.in);
	        AmazonS3 s3 = new AmazonS3Client(credentials);  //creating the s3 client
	        S3Service service = new S3Service();
	       
	        int choice=0;
	        String bucketName=null;
	        String filePath=null;
	        String key=null;
	        
	        try
	        {
	        while(true)
	        {
	        	System.out.println("Please enter your choice:");
	        	System.out.println("1	: Listing your Bucket ");
	        	System.out.println("2	: Creating new Bucket ");
	        	System.out.println("3	: Delete existing Bucket");
	        	System.out.println("4	: Upload the file in your bucket");
	        	System.out.println("5	: listing the objects in your bucket");
	        	System.out.println("9	: exit ");
	        	choice = scanner.nextInt();
	        	switch(choice)
	        	{
	        		case 1:
	        			 service.listingBuckets(s3);         //listing the bucket
	        			 break;
	        		case 2:
	        			System.out.println("Please enter your bucket Name :");
	        			bucketName = scanner.next();
	        			service.createS3Bucket(bucketName, s3);
	        			break;
	        		case 3 :
	        			System.out.println("Please enter your bucket name which you want to delete :");
	        			bucketName = scanner.next();
	        			service.deleteS3Bucket(bucketName, s3);
	        			break;
	        		case 4:
	        			System.out.println("Enter the bucket name in which you wan to upload the file :");
	        			bucketName = scanner.next();
	        			if(!s3.doesBucketExist(bucketName))
	        				{
	        				System.err.println("Sorry! this bucket does not exist in your account");
	        				continue;
	        				}
	        			System.out.println("Enter the complete path of your file :");
	        			filePath = scanner.next();
	        			System.out.println("Enter the key for your file");
	        			key = scanner.next();
	        			service.fileUploadinS3(bucketName, filePath, s3,key);
	        			break;
	        		case 5:
	        			System.out.println("Enter the bucket name in which the contents you want see :");
	        			bucketName = scanner.next();
	        			service.listBucketObjects(bucketName, s3);
	        			break;
	        		case 9:
	        		    System.out.println("Thank you for using s3 service");
	        		    System.exit(0);
	        	}
	        	
	        	
	   	
	        }
	       }
	        catch(AmazonClientException ex)
	        {
	        	System.err.println(ex.getMessage());
	        	
	        }
	        
	        


	}

}
