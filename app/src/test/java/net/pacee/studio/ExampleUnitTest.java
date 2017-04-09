package net.pacee.studio;

import android.net.Uri;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


import static net.pacee.studio.Utils.ConnectionUtils.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void helloThere() throws Exception
    {

        String json = getResponseFromHttpUrl(getBaseUrl());
        JSONObject jo = new JSONObject(json);
        System.out.println(json);
    }
}