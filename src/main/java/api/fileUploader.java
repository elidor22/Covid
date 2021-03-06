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

        String fileName = getSaltString();
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



    public static void main(String args[]){
        fileUploader upload = new fileUploader();


    }


    //Generates a random string
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 140) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
