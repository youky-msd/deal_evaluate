package spout;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.List;

public class StringScheme implements Scheme {
    @Override
    public List<Object> deserialize(byte[] bytes) {
        try {
            return new Values(new String(bytes));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public Fields getOutputFields() {
        return new Fields("line");
    }
}
