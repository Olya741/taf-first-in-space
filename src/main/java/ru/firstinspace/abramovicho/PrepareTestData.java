package ru.firstinspace.abramovicho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrepareTestData {
    static List<String> getListOfDomains() {
        List<String> domains = new ArrayList<>();
        Collections.addAll(domains, ".com", ".net", ".edu", ".org", ".info");
        return domains;
    }

    static List<String> getListOfNames() {
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "iris", "andrew", "mark", "lily", "emma", "oscar");
        return names;
    }

    static List<String> getListOfSites() {
        List<String> sites = new ArrayList<>();
        Collections.addAll(sites, "bbc", "onliner", "hp", "shops");
        return sites;
    }

}
