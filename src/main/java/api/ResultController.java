package api;

import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * Handles the HTTP requests and basically connects the whole program*/

@RestController
class ResultController {
	static String url ="jdbc:db2://dashdb-txn-sbox-yp-lon02-04.services.eu-gb.bluemix.net:50001/BLUDB:sslConnection=true;;";
	static String username = "fsf96983";
	static String password ="2lcn+8xjbkrdvg2p";
	private final UserRepository repository;
	public byte[] test = new byte[0];
	ResultController(UserRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	// tag::get-aggregate-root[]


	//Returns the list of results
	@CrossOrigin(origins = "*")
	@GetMapping("/result")
	@ResponseBody
	Resources<ArrayList> all() throws SQLException {


		return new Resources<ArrayList>(Collections.singleton(DatabaseConnector.queryAll()));
	}
	// end::get-aggregate-root[]

	//Gets a post request from the user and returns the information based on the uploaded image
	//Also handles the upload and calls the classifier class
	/*@PostMapping("/result")
	Result newUser(@RequestBody Result newResult) throws IOException, InterruptedException {
		System.out.println(newResult.getCov_ind());
		DatabaseConnector dbc = new DatabaseConnector();
		fileUploader uploader= new fileUploader();
		String url=uploader.upload(newResult.getUri(),"random");
		//Runs the classification
		ClassifierWrapper classifierWrapper = new ClassifierWrapper();
		List<String>ls =classifierWrapper.run(url);
		dbc.insert(ls.get(0), ls.get(1), ls.get(2),classifierWrapper.classify+""
				,url);
		//Set return parameters so that the api returns the correct information
		newResult.setUri(url);
		newResult.setResult(classifierWrapper.classify+"");
		String cov = ls.get(0);String nor = ls.get(1);String pneumonia = ls.get(2);
		newResult.setCov_ind(cov);
		newResult.setNormal_ind(nor);
		newResult.setPneumonia_ind(pneumonia);
		return repository.save(newResult);
	}*/

	@CrossOrigin(origins = "*")
	@PostMapping("/result")
	public Result handleFileUpload(@RequestParam("file") MultipartFile file,
								   RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
		Result newResult = new Result();

		DatabaseConnector dbc = new DatabaseConnector();
		fileUploader uploader= new fileUploader();

		String url=uploader.upload(file.getInputStream(),file.getName());
		//Runs the classification
		ClassifierWrapper classifierWrapper = new ClassifierWrapper();
		List<String>ls =classifierWrapper.run(url);
		dbc.insert(ls.get(0), ls.get(1), ls.get(2),classifierWrapper.classify+""
				,url);
		//Set return parameters so that the api returns the correct information
		newResult.setUri(url);
		newResult.setResult(classifierWrapper.classify+"");
		String cov = ls.get(0);String nor = ls.get(1);String pneumonia = ls.get(2);
		newResult.setCov_ind(cov);
		newResult.setNormal_ind(nor);
		newResult.setPneumonia_ind(pneumonia);

		return repository.save(newResult);
	}


}
