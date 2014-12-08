package jp.gmor.research.ad.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;


import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * aguPanelUtils テストケース
 * @author usr160056
 * @since 2014/12/04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test-config.xml" })
public class SaguPanelUtilsTest {

    /**
     * 初期処理
     */
    @Before
    public void setup() {
    }

    /**
     * 正しく統一IDが取得できる
     * @throws Exception 例外
     */
    @Test
    public void monitor_idとpanel_typeからjn_monitor_idが取得できる() throws Exception {
        assertThat(SaguPanelUtils.convertToJnMonitorID("99999999", "20"), is("2099999999"));
    }

    /**
     * パネル種別の先頭ゼロは統一IDでは除去される
     * @throws Exception 例外
     */
    @Test
    public void panel_typeの先頭のゼロは除去される() throws Exception {
        assertThat(SaguPanelUtils.convertToJnMonitorID("99999999", "01"), is("199999999"));
    }

    /**
     * 8桁に満たないモニターIDは8桁まで左ゼロ埋めされて統一IDが生成される
     * @throws Exception 例外
     */
    @Test
    public void monitor_idが8桁未満の場合は8桁まで左ゼロ埋めされる() throws Exception {
        assertThat(SaguPanelUtils.convertToJnMonitorID("1", "30"), is("3000000001"));
    }
//
//    /**
//     * カバレッジのためにコンストラクタを呼ぶ
//     * @throws Exception 例外
//     */
//    @Test
//    public void インスタンスが生成出来る() throws Exception {
//        assertThat(new SaguPanelUtils(), is(instanceOf(SaguPanelUtils.class)));
//    }

}
