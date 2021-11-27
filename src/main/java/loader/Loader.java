package loader;

import com.opencsv.bean.CsvToBeanBuilder;
import contracts.contractProxy.ContractProxy;
import repository.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

public class Loader {

    public Loader() {
    }

    public static void load(String path, Repository repo) throws FileNotFoundException {
        var proxies = new CsvToBeanBuilder<ContractProxy>(new FileReader(path))
                .withType(ContractProxy.class).build().parse();

        var contracts = proxies.stream().map(ContractProxy::turnIntoContract).collect(Collectors.toList());
        contracts.forEach(repo::addContracts);
    }
}
