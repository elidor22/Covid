package api;

import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
class ResultController {
	static String url ="jdbc:db2://dashdb-txn-sbox-yp-lon02-04.services.eu-gb.bluemix.net:50001/BLUDB:sslConnection=true;;";
	static String username = "fsf96983";
	static String password ="2lcn+8xjbkrdvg2p";
	private final UserRepository repository;

	ResultController(UserRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	// tag::get-aggregate-root[]


	@GetMapping("/users")
	@ResponseBody
	Resources<ArrayList> all() throws SQLException {


		return new Resources<ArrayList>(Collections.singleton(DatabaseConnector.queryAll()));
	}
	// end::get-aggregate-root[]

	@PostMapping("/users")
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
	}


	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
