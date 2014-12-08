package jp.gmor.research.ad.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * AdTruthController テストケース
 * @author usr160056
 * @since 2014/12/04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application-test-config.xml" })
public class AdTruthControllerTest {

    private MockMvc mockMvc;

    /**
     * 初期処理
     */
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AdTruthController()).build();
    }

    /**
     * 正しいパラメータで呼び出した場合にhttpステータス200を返すこと
     * @throws Exception 例外
     */
    @Test
    public void AdTruthのIDの生成リクエストが正常に受付できる() throws Exception {
        this.mockMvc.perform(get("/{id}/{panel}/{country}/{deviceinfo}", 1, "2", "3", "4").accept(MediaType.TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("success"));
    }

    /**
     * deviceinfoがnullの場合にはhttpステータス404(NotFound)が返却される
     * @throws Exception 例外
     */
    @Test
    public void AdTruthのIDの生成リクエストURLが不正な場合は404がかえる() throws Exception {
        this.mockMvc.perform(get("/{id}/{panel}/{country}/{deviceinfo}", 1, "2", "3", null).accept(MediaType.TEXT_HTML)).andExpect(status().isNotFound());
        this.mockMvc.perform(get("/{id}/{panel}/{country}/{deviceinfo}", 1, "2", null, "4").accept(MediaType.TEXT_HTML)).andExpect(status().isNotFound());
        this.mockMvc.perform(get("/{id}/{panel}/{country}/{deviceinfo}", 1, null, "3", "4").accept(MediaType.TEXT_HTML)).andExpect(status().isNotFound());
        this.mockMvc.perform(get("/{id}/{panel}/{country}/{deviceinfo}", null, "2", "3", "4").accept(MediaType.TEXT_HTML)).andExpect(status().isNotFound());
    }

    /**
     * idが数値でない場合はhttpステータス400(BadRequest)が返却される
     * @throws Exception 例外
     */
    @Test
    public void idが数値でない場合は400が返る() throws Exception {
        this.mockMvc.perform(get("/{id}/{panel}/{country}/{deviceinfo}", "A", "2", "3", "4").accept(MediaType.TEXT_HTML)).andExpect(status().isBadRequest());
    }
}
