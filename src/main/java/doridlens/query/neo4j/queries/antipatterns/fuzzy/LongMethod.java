package doridlens.query.neo4j.queries.antipatterns.fuzzy;

import doridlens.query.neo4j.queries.QueryPropertiesReader;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: MaoMorn
 * Date: 2018/9/20
 * Time: 22:27
 * Description:
 */
public class LongMethod extends FuzzyQuery {

    public static final String KEY = "LM";

    public LongMethod(QueryPropertiesReader reader) {
        super(KEY, "LongMethod.fcl", reader);
    }

    /*
        MATCH (m:Method) WHERE m.number_of_instructions > veryHigh
        RETURN m.app_key as app_key

        details -> m.full_name as full_name
        else -> count(m) as LM
     */

    @Override
    public String getQuery(boolean details) {
        String query = getLMNodes(reader.get("Long_method_noi_veryHigh"));
        query += "RETURN m.app_key as app_key,";
        if (details) {
            query += "m.full_name as full_name";
        } else {
            query += "count(m) as LM";
        }
        return query;
    }

    /*
        MATCH (m:Method) WHERE m.number_of_instructions > high
        RETURN m.app_key as app_key,m.number_of_instructions as number_of_instructions,

        details -> m.full_name as full_name
     */

    @Override
    public String getFuzzyQuery(boolean details) {
        String query = getLMNodes(reader.get("Long_method_noi_high"));
        query += "RETURN m.app_key as app_key,m.number_of_instructions as number_of_instructions";
        if (details) {
            query += ",m.full_name as full_name";
        }
        return query;
    }

    private String getLMNodes(double threshold) {
        return "MATCH (m:Method) WHERE m.number_of_instructions > " + threshold + "\n";
    }

    @Override
    public List<Map<String, Object>> getFuzzyResult(List<Map<String, Object>> result, FIS fis) {
        int cc;
        List<Map<String, Object>> fuzzyResult = new ArrayList<>();
        FunctionBlock fb = fis.getFunctionBlock(null);
        for (Map<String, Object> res : result) {
            cc = (int) res.get("number_of_instructions");
            if (cc >= reader.get("Long_method_noi_veryHigh")) {
                res.put("fuzzy_value", 1);
            } else {
                fb.setVariable("number_of_instructions", cc);
                fb.evaluate();
                res.put("fuzzy_value", fb.getVariable("res").getValue());
            }
            fuzzyResult.add(res);
        }
        return fuzzyResult;
    }

}