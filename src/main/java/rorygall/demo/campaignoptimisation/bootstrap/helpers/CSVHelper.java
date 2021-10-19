package rorygall.demo.campaignoptimisation.bootstrap.helpers;

import rorygall.demo.campaignoptimisation.entity.Campaign;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVHelper {

    private CSVHelper() {
        // not for instantiating - helper class
        throw new UnsupportedOperationException();
    }

    public static List<Campaign> buildGroupsFromCSV(final InputStream is, final CampaignGroup campaignGroup) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             final CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            final List<Campaign> campaigns = new ArrayList<>();

            final Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (final CSVRecord csvRecord : csvRecords) {
                final Campaign campaign = new Campaign(0,
                        csvRecord.get("name"),
                        new BigDecimal(csvRecord.get("budget")),
                        Integer.parseInt(csvRecord.get("impressions")),
                        new BigDecimal(0.0),
                        null,
                        null
                );

                campaign.setRevenue(new BigDecimal(0));

                campaign.setGroup(campaignGroup);
                campaigns.add(campaign);
            }

            return campaigns;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
