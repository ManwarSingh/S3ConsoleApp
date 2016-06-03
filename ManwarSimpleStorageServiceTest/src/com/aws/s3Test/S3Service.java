/*
 * This is a simple java project which is able to access the
 * AWS S3 service
 *  
 *  Manwar Singh © 2016
 * 
 */





package com.aws.s3Test;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
public class S3Service {
	
	
	
	/*********************************************************************************
	 * Author 			: Manwar Singh
	 * Method Name		: createS3Bucket()
	 * Parameters		: String,AmazonS3
	 * Return type		: Bucket	
	 * Description		: Creation of bucket and will display the bucket id
	 * Creation date	: 6/3/2016
	 * Last modification	:  
	 */
	public  Bucket createS3Bucket(String bucketName,AmazonS3 as) throws AmazonClientException,AmazonServiceException
	{ 
		if(as.doesBucketExist(bucketName))
		{
			System.err.println("Bucket already exist");
			return null;
		}
		Bucket bucket = as.createBucket(bucketName);
		
		if(bucket != null)
		System.out.println("Bucket created successfully");
		return bucket;
	}
	
	/*********************************************************************************
	 * Author 			: Manwar Singh
	 * Method Name		: deleteS3Bucket()
	 * Parameters		: String,AmazonS3
	 * Return type		: boolean	
	 * Description		: deletion of bucket from your account
	 * Creation date	: 6/3/2016
	 * Last modification	:  
	 */
	public boolean deleteS3Bucket(String bucketName, AmazonS3 as)
	{
		if(!as.doesBucketExist(bucketName))
			{
			System.err.println(bucketName+" bucket does not exist in your account");
			return false;
			}
		else
			{
			as.deleteBucket(bucketName);
			System.out.println(bucketName + " bucket deleted successfully from your amazon account");
			}		
		return true;
	}
	
	/*********************************************************************************
	 * Author 			: Manwar Singh
	 * Method Name		: fileUploadinS3()
	 * Parameters		: String bucketName,String filePath, AmazonS3 s3,String key
	 * Return type		: boolean	
	 * Description		: uploading file into bucket
	 * Creation date	: 6/3/2016
	 * Last modification	:  
	 */
	public boolean fileUploadinS3(String bucketName,String filePath, AmazonS3 s3,String key)throws AmazonServiceException,AmazonClientException
	{
		if(!s3.doesBucketExist(bucketName))
		{
		System.err.println("Bucket does not exist");
		return false;
		}
		File file = new File(filePath);
		s3.putObject(bucketName, key, file);
		System.out.println("your file is uploaded successfully");
		return true;
	}
	
	

	/*********************************************************************************
	 * Author 			: Manwar Singh
	 * Method Name		: listBucketObjects
	 * Parameters		: String bucketName,AmazonS3 as
	 * Return type		: void	
	 * Description		: listing the objects in the bucket
	 * Creation date	: 6/3/2016
	 * Last modification	:  
	 */
	
	public boolean listBucketObjects(String bucketName,AmazonS3 as)
	{ 
		
		
		if(!as.doesBucketExist(bucketName))
			{
			System.err.println("Bucket does not exist");
			return false;
			}
		ObjectListing objectsOfBucket=as.listObjects(bucketName);
		if(objectsOfBucket == null)
		{
			System.err.println("No objects in your buckets");
		}
		System.out.println("The list of object in your bucket");
		System.out.println("Bucket Name \t\t\t Key \t\t\t\t Size");
		
		  for(S3ObjectSummary summary: objectsOfBucket.getObjectSummaries())
			System.out.println(summary.getBucketName()+"\t"+summary.getKey()+"\t"+summary.getSize());
		return true;
	}
	
	
	
	
	
	
	/*********************************************************************************
	 * Author 			: Manwar Singh
	 * Method Name		: listingBuckets()]
	 * Parameters		: AmazonS3
	 * Return type		: void	
	 * Description		: Display the list of buckets with name and creation date in your account
	 * Creation date	: 6/3/2016
	 * Last modification	:  
	 */
	
	public void  listingBuckets( AmazonS3 as)
	{
		System.out.println("The list of bucket in your account are : ");
		List<Bucket> s3Bukets = as.listBuckets();
		System.out.println("**********************************************************************************************");
		
		System.out.println("Bucket Name" +"\t\t\t\t\t\t\t"+"Bucket Creation Date");
		System.out.println("*******************************************************************************************************");
		for(Bucket bucket : s3Bukets)
		{
			System.out.println(bucket.getName()+"\t\t\t\t\t\t"+bucket.getCreationDate()+"\t");
		}
		
		System.out.println("***********************************************************************");	
	}
	
	

}
