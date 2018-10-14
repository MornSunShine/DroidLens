package doridlens.query.neo4j.queries.antipatterns.fuzzy;

import doridlens.query.neo4j.queries.QueryPropertiesReader;

/**
 * Author: MaoMorn
 * Date: 2018/9/20
 * Time: 22:26
 * Description:
 */
public class HeavyAsyncTaskSteps extends HeavySomethingQuery {

    public static final String KEY = "HAS";

    public HeavyAsyncTaskSteps(QueryPropertiesReader reader) {
        super(KEY, reader);
    }

    /*
        MATCH (c:Class{parent_name:'android.os.AsyncTask'})-[:CLASS_OWNS_METHOD]->(m:Method)
        WHERE (m.name='onPreExecute' OR m.name='onProgressUpdate' OR m.name='onPostExecute')
            AND m.number_of_instructions > veryHigh_noi
            AND m.cyclomatic_complexity > veryHigh_cc
        RETURN m.app_key as app_key

        details -> m.full_name as full_name
        else -> count(m) as HAS
     */

    @Override
    public String getQuery(boolean details) {
        String query = getAsyncStepNodes(reader.get("Heavy_class_veryHigh_noi"),
                reader.get("Heavy_class_veryHigh_cc"));
        query += "RETURN m.app_key as app_key,";
        if (details) {
            query += "m.full_name as full_name";
        } else {
            query += "count(m) as HAS";
        }
        return query;
    }

    /*
        MATCH (c:Class{parent_name:'android.os.AsyncTask'})-[:CLASS_OWNS_METHOD]->(m:Method)
        WHERE (m.name='onPreExecute' OR m.name='onProgressUpdate' OR m.name='onPostExecute')
            AND m.number_of_instructions > high_noi
            AND m.cyclomatic_complexity > high_cc
        RETURN m.app_key as app_key, m.cyclomatic_complexity as cyclomatic_complexity,
            m.number_of_instructions as number_of_instructions

        details -> m.full_name as full_name
     */

    @Override
    public String getFuzzyQuery(boolean details) {
        String query = getAsyncStepNodes(reader.get("Heavy_class_high_noi"),
                reader.get("Heavy_class_high_cc"));
        query += "RETURN m.app_key as app_key, m.cyclomatic_complexity as cyclomatic_complexity,\n" +
                "m.number_of_instructions as number_of_instructions";
        if (details) {
            query += ",m.full_name as full_name";
        }
        return query;
    }

    private String getAsyncStepNodes(double noiThreshold, double ccThreshold) {
        return " MATCH (c:Class{parent_name:'android.os.AsyncTask'})-[:CLASS_OWNS_METHOD]->(m:Method)\n" +
                "WHERE (m.name='onPreExecute' OR m.name='onProgressUpdate' OR m.name='onPostExecute')\n" +
                "   AND m.number_of_instructions > " + noiThreshold + "\n" +
                "   AND m.cyclomatic_complexity > " + ccThreshold + "\n";
    }

}