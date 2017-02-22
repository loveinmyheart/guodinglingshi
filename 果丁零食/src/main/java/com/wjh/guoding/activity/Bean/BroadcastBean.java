package com.wjh.guoding.activity.Bean;

import java.util.List;

/**
 * Created by lenovo on 2017/2/18.
 */
public class BroadcastBean {

    /**
     * result : {"banner":[{"type":"1","display_type":"1","pic":"http://ww1.sinaimg.cn/large/006y8mN6gw1fa8u1bxg3lj30ku0b00uf.jpg","name":"果丁『作者计划』指南","attach":{"url":"http://www.guodingapp.com/author/index.html"}},{"type":"3","display_type":"1","pic":"https://ww1.sinaimg.cn/large/006tNbRwgw1fbi6mppo1zj30ku0b0wf5.jpg","name":"生活达人之营养早餐","attach":{"id":"298","action":"cat"}},{"type":"3","display_type":"1","pic":"http://ww2.sinaimg.cn/large/006y8lVagw1faj8ze7avzj30ku0b0whx.jpg","name":"少女心爆棚的小零食","attach":{"id":"269","action":"cat"}}]}
     * code : 0
     * error :
     * time : 1487382072
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
         * banner : [{"type":"1","display_type":"1","pic":"http://ww1.sinaimg.cn/large/006y8mN6gw1fa8u1bxg3lj30ku0b00uf.jpg","name":"果丁『作者计划』指南","attach":{"url":"http://www.guodingapp.com/author/index.html"}},{"type":"3","display_type":"1","pic":"https://ww1.sinaimg.cn/large/006tNbRwgw1fbi6mppo1zj30ku0b0wf5.jpg","name":"生活达人之营养早餐","attach":{"id":"298","action":"cat"}},{"type":"3","display_type":"1","pic":"http://ww2.sinaimg.cn/large/006y8lVagw1faj8ze7avzj30ku0b0whx.jpg","name":"少女心爆棚的小零食","attach":{"id":"269","action":"cat"}}]
         */

        private List<BannerEntity> banner;

        public void setBanner(List<BannerEntity> banner) {
            this.banner = banner;
        }

        public List<BannerEntity> getBanner() {
            return banner;
        }

        public static class BannerEntity {
            /**
             * type : 1
             * display_type : 1
             * pic : http://ww1.sinaimg.cn/large/006y8mN6gw1fa8u1bxg3lj30ku0b00uf.jpg
             * name : 果丁『作者计划』指南
             * attach : {"url":"http://www.guodingapp.com/author/index.html"}
             */

            private String type;
            private String display_type;
            private String pic;
            private String name;
            private AttachEntity attach;

            public void setType(String type) {
                this.type = type;
            }

            public void setDisplay_type(String display_type) {
                this.display_type = display_type;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setAttach(AttachEntity attach) {
                this.attach = attach;
            }

            public String getType() {
                return type;
            }

            public String getDisplay_type() {
                return display_type;
            }

            public String getPic() {
                return pic;
            }

            public String getName() {
                return name;
            }

            public AttachEntity getAttach() {
                return attach;
            }

            public static class AttachEntity {
                /**
                 * url : http://www.guodingapp.com/author/index.html
                 */

                private String url;

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getUrl() {
                    return url;
                }
            }
        }
    }
}
