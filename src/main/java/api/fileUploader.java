package api;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;

import java.io.*;
import java.util.Random;

public class fileUploader {
    public static String url;

    public String upload(InputStream file, String name) throws IOException {
        String temp = (new Random().toString()) + " " + name + ".jpg";

        OutputStream outputStream = new FileOutputStream(temp);
        file.transferTo(outputStream);
        String connectStr = "DefaultEndpointsProtocol=https;AccountName=covidclassifyfeeder;AccountKey=j7/F8DYfkjWVeISH7n5Kber/V+NJztCoEreaJiWO0w5sSifSBuLXzMvGGQhGez2b3CaKBdDNbjk0eGqD41fimg==;EndpointSuffix=core.windows.net";


        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectStr).buildClient();

//Create a unique name for the container
        String containerName = "covidimages";

// Create the container and return a container client object
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        Random rd = new Random(800000);
        Random rd2 = new Random(871021540);
        String fileName = "   "+rd.toString()+rd2.toString();
        // File localFile = new File(localPath + fileName);

// Get a reference to a blob
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

// Upload the blob
        BlobHttpHeaders hd = new BlobHttpHeaders();
        blobClient.uploadFromFile(temp);
        //Sets the content type so it is displayed as image and not downloaded as octet that is the Azure default header
        hd.setContentType("Content-Type: image/jpeg");
        blobClient.setHttpHeaders(hd);

        String uri = blobClient.getBlobUrl();
        url = uri;

        // delete file tmep
        File f = new File(temp);
        f.delete();

        return blobClient.getBlobUrl();
    }


    public String upload(String path, String name){
        String connectStr = "DefaultEndpointsProtocol=https;AccountName=covidclassifyfeeder;AccountKey=j7/F8DYfkjWVeISH7n5Kber/V+NJztCoEreaJiWO0w5sSifSBuLXzMvGGQhGez2b3CaKBdDNbjk0eGqD41fimg==;EndpointSuffix=core.windows.net";


        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectStr).buildClient();

//Create a unique name for the container
        String containerName = "covidimages";

// Create the container and return a container client object
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        String localPath = path;
        Random rd = new Random(800000);
        Random rd2 = new Random(871021540);
        String fileName = "   "+rd.toString()+rd2.toString();
        // File localFile = new File(localPath + fileName);

// Get a reference to a blob
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

// Upload the blob
        BlobHttpHeaders hd = new BlobHttpHeaders();
        localPath = localPath.replace("\\","/");
        blobClient.uploadFromFile(localPath);
        //Sets the content type so it is displayed as image and not downloaded as octet that is the Azure default header
        hd.setContentType("Content-Type: image/jpeg");
        blobClient.setHttpHeaders(hd);

        String uri = blobClient.getBlobUrl();
        url = uri;
        return blobClient.getBlobUrl();

    }

    public static void main(String args[]){
        fileUploader upload = new fileUploader();

        System.out.println(upload.upload("output.jpg","test"));

    }
}
