package com.wjh.guoding.activity.Bean;

import java.util.List;

/**
 * Created by lenovo on 2017/2/21.
 */
public class DiscoverBean {

    /**
     * result : {"tag_list":[{"tag_id":"44","tag_name":"暖冬好物","image":"http://ww1.sinaimg.cn/large/006y8lVagw1fai8v3nsczj30ei076gmm.jpg"},{"tag_id":"43","tag_name":"本周热门","image":"http://ww3.sinaimg.cn/large/801b780agw1f7z4uhkxyoj20ei076dgq.jpg"},{"tag_id":"42","tag_name":"精致糖果","image":"http://ww1.sinaimg.cn/large/801b780agw1f7z4ucd3emj20ei076wg3.jpg"},{"tag_id":"41","tag_name":"颜值好物","image":"http://ww1.sinaimg.cn/large/801b780agw1f7z4ttdoqzj20ei076jsi.jpg"},{"tag_id":"40","tag_name":"煲剧必备","image":"http://ww2.sinaimg.cn/large/801b780agw1f7z4ukw09ej20ei076jsx.jpg"},{"tag_id":"39","tag_name":"心爱甜食","image":"http://ww4.sinaimg.cn/large/801b780agw1f7z4tvgrkuj20ei0763zc.jpg"},{"tag_id":"38","tag_name":"异国风情","image":"http://ww4.sinaimg.cn/large/801b780agw1f7z4tqqvd9j20ei076jsp.jpg"},{"tag_id":"37","tag_name":"营养好物","image":"http://ww1.sinaimg.cn/large/801b780agw1f7z4tnybe7j20ei076q47.jpg"},{"tag_id":"36","tag_name":"肉食集聚地","image":"http://ww2.sinaimg.cn/large/801b780agw1f7z4tzl9kej20ei076mz3.jpg"},{"tag_id":"35","tag_name":"办公室必备","image":"http://ww2.sinaimg.cn/large/801b780agw1f7z4unjadbj20ei076myf.jpg"},{"tag_id":"34","tag_name":"地域风情","image":"http://ww3.sinaimg.cn/large/801b780agw1f7z4uffyjrj20ei076myp.jpg"},{"tag_id":"33","tag_name":"美味速食","image":"http://ww4.sinaimg.cn/large/801b780agw1f7z4u5wq5jj20ei0760u9.jpg"},{"tag_id":"32","tag_name":"悠闲下午茶","image":"http://ww3.sinaimg.cn/large/801b780agw1f7z4tjf9mkj20ei076abj.jpg"},{"tag_id":"31","tag_name":"清爽夏季","image":"http://ww3.sinaimg.cn/large/801b780agw1f7z4u2w6qsj20ei076ab2.jpg"},{"tag_id":"30","tag_name":"唠嗑必备","image":"http://ww4.sinaimg.cn/large/801b780agw1f7z4u8w1ovj20ei076gmz.jpg"}]}
     * code : 0
     * error :
     * time : 1487678388
     * msg_count : 0
     */

    private ResultEntity result;
    private int code;
    private String error;
    private int time;
    private int msg_count;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setMsg_count(int msg_count) {
        this.msg_count = msg_count;
    }

    public ResultEntity getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public int getTime() {
        return time;
    }

    public int getMsg_count() {
        return msg_count;
    }

    public static class ResultEntity {
        /**
         * tag_list : [{"tag_id":"44","tag_name":"暖冬好物","image":"http://ww1.sinaimg.cn/large/006y8lVagw1fai8v3nsczj30ei076gmm.jpg"},{"tag_id":"43","tag_name":"本周热门","image":"http://ww3.sinaimg.cn/large/801b780agw1f7z4uhkxyoj20ei076dgq.jpg"},{"tag_id":"42","tag_name":"精致糖果","image":"http://ww1.sinaimg.cn/large/801b780agw1f7z4ucd3emj20ei076wg3.jpg"},{"tag_id":"41","tag_name":"颜值好物","image":"http://ww1.sinaimg.cn/large/801b780agw1f7z4ttdoqzj20ei076jsi.jpg"},{"tag_id":"40","tag_name":"煲剧必备","image":"http://ww2.sinaimg.cn/large/801b780agw1f7z4ukw09ej20ei076jsx.jpg"},{"tag_id":"39","tag_name":"心爱甜食","image":"http://ww4.sinaimg.cn/large/801b780agw1f7z4tvgrkuj20ei0763zc.jpg"},{"tag_id":"38","tag_name":"异国风情","image":"http://ww4.sinaimg.cn/large/801b780agw1f7z4tqqvd9j20ei076jsp.jpg"},{"tag_id":"37","tag_name":"营养好物","image":"http://ww1.sinaimg.cn/large/801b780agw1f7z4tnybe7j20ei076q47.jpg"},{"tag_id":"36","tag_name":"肉食集聚地","image":"http://ww2.sinaimg.cn/large/801b780agw1f7z4tzl9kej20ei076mz3.jpg"},{"tag_id":"35","tag_name":"办公室必备","image":"http://ww2.sinaimg.cn/large/801b780agw1f7z4unjadbj20ei076myf.jpg"},{"tag_id":"34","tag_name":"地域风情","image":"http://ww3.sinaimg.cn/large/801b780agw1f7z4uffyjrj20ei076myp.jpg"},{"tag_id":"33","tag_name":"美味速食","image":"http://ww4.sinaimg.cn/large/801b780agw1f7z4u5wq5jj20ei0760u9.jpg"},{"tag_id":"32","tag_name":"悠闲下午茶","image":"http://ww3.sinaimg.cn/large/801b780agw1f7z4tjf9mkj20ei076abj.jpg"},{"tag_id":"31","tag_name":"清爽夏季","image":"http://ww3.sinaimg.cn/large/801b780agw1f7z4u2w6qsj20ei076ab2.jpg"},{"tag_id":"30","tag_name":"唠嗑必备","image":"http://ww4.sinaimg.cn/large/801b780agw1f7z4u8w1ovj20ei076gmz.jpg"}]
         */

        private List<TagListEntity> tag_list;

        public void setTag_list(List<TagListEntity> tag_list) {
            this.tag_list = tag_list;
        }

        public List<TagListEntity> getTag_list() {
            return tag_list;
        }

        public static class TagListEntity {
            /**
             * tag_id : 44
             * tag_name : 暖冬好物
             * image : http://ww1.sinaimg.cn/large/006y8lVagw1fai8v3nsczj30ei076gmm.jpg
             */

            private String tag_id;
            private String tag_name;
            private String image;

            public void setTag_id(String tag_id) {
                this.tag_id = tag_id;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTag_id() {
                return tag_id;
            }

            public String getTag_name() {
                return tag_name;
            }

            public String getImage() {
                return image;
            }
        }
    }
}

