package loader;

import com.opencsv.bean.CsvToBeanBuilder;
import contracts.contractProxy.ContractProxy;
import repository.Repository;
import validator.Status;
import validator.Validator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

public class Loader {

    public Loader() {
    }

    /**
     * static method for loading data from .csv file
     * @param path Path to .csv file
     * @param repo Object of class Repository
     * @throws FileNotFoundException throws if file is not found
     */
    public static void load(String path, Repository repo) throws FileNotFoundException {
        var proxies = new CsvToBeanBuilder<ContractProxy>(new FileReader(path))
                .withType(ContractProxy.class).build().parse();

        var contracts = proxies.stream()
                .map(ContractProxy::turnIntoContract)
                .filter(x -> new Validator(x).validate().getStatus().equals(Status.OK))
                .collect(Collectors.toList());
        contracts.forEach(repo::addContracts);
    }
}
