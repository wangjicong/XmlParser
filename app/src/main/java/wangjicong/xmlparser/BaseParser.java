package wangjicong.xmlparser;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface BaseParser<E> {
    public List<E> parse(InputStream inputStream) throws Exception;
    public String serialize(List<E> data) throws Exception;
}
