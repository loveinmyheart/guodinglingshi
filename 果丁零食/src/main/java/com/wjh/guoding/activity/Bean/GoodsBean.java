package com.wjh.guoding.activity.Bean;

import java.util.List;

/**
 * Created by lenovo on 2017/2/21.
 */
public class GoodsBean {

    /**
     * result : {"detail":{"tao_pwd":"￥UgURSVGB8E￥","cat_id":"19","goods_collect":"482","add_time":"1484390814","shop_price":"8.90","goods_image":"http://img.guodingapp.com/pic/633dcd3124a75aca95e90d336a5f1a68.jpg?imageMogr2/thumbnail/600x600/quality/95","is_coupon":"1","cmt_count":"0","goods_name":"锅巴好吃的手工香辣油炸膨化食品饼干零食锅巴特产年货特价","seller_shop_site":"http://c.b1za.com/h.2astFn?cv=UgURSVGB8E","goods_brief":"","goods_click":"0","market_price":"11.90","goods_id":"2268","shipping_fee":"0.00","album":["http://img.guodingapp.com/pic/633dcd3124a75aca95e90d336a5f1a68.jpg?imageMogr2/thumbnail/600x600/quality/95"],"is_collect":0,"share_url":"http://www.guodingapp.com/goods/detail?goods_id=2268"}}
     * code : 0
     * error :
     * time : 1487666875
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
         * detail : {"tao_pwd":"￥UgURSVGB8E￥","cat_id":"19","goods_collect":"482","add_time":"1484390814","shop_price":"8.90","goods_image":"http://img.guodingapp.com/pic/633dcd3124a75aca95e90d336a5f1a68.jpg?imageMogr2/thumbnail/600x600/quality/95","is_coupon":"1","cmt_count":"0","goods_name":"锅巴好吃的手工香辣油炸膨化食品饼干零食锅巴特产年货特价","seller_shop_site":"http://c.b1za.com/h.2astFn?cv=UgURSVGB8E","goods_brief":"","goods_click":"0","market_price":"11.90","goods_id":"2268","shipping_fee":"0.00","album":["http://img.guodingapp.com/pic/633dcd3124a75aca95e90d336a5f1a68.jpg?imageMogr2/thumbnail/600x600/quality/95"],"is_collect":0,"share_url":"http://www.guodingapp.com/goods/detail?goods_id=2268"}
         */

        private DetailEntity detail;

        public void setDetail(DetailEntity detail) {
            this.detail = detail;
        }

        public DetailEntity getDetail() {
            return detail;
        }

        public static class DetailEntity {
            /**
             * tao_pwd : ￥UgURSVGB8E￥
             * cat_id : 19
             * goods_collect : 482
             * add_time : 1484390814
             * shop_price : 8.90
             * goods_image : http://img.guodingapp.com/pic/633dcd3124a75aca95e90d336a5f1a68.jpg?imageMogr2/thumbnail/600x600/quality/95
             * is_coupon : 1
             * cmt_count : 0
             * goods_name : 锅巴好吃的手工香辣油炸膨化食品饼干零食锅巴特产年货特价
             * seller_shop_site : http://c.b1za.com/h.2astFn?cv=UgURSVGB8E
             * goods_brief :
             * goods_click : 0
             * market_price : 11.90
             * goods_id : 2268
             * shipping_fee : 0.00
             * album : ["http://img.guodingapp.com/pic/633dcd3124a75aca95e90d336a5f1a68.jpg?imageMogr2/thumbnail/600x600/quality/95"]
             * is_collect : 0
             * share_url : http://www.guodingapp.com/goods/detail?goods_id=2268
             */

            private String tao_pwd;
            private String cat_id;
            private String goods_collect;
            private String add_time;
            private String shop_price;
            private String goods_image;
            private String is_coupon;
            private String cmt_count;
            private String goods_name;
            private String seller_shop_site;
            private String goods_brief;
            private String goods_click;
            private String market_price;
            private String goods_id;
            private String shipping_fee;
            private int is_collect;
            private String share_url;
            private List<String> album;

            public void setTao_pwd(String tao_pwd) {
                this.tao_pwd = tao_pwd;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public void setGoods_collect(String goods_collect) {
                this.goods_collect = goods_collect;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public void setIs_coupon(String is_coupon) {
                this.is_coupon = is_coupon;
            }

            public void setCmt_count(String cmt_count) {
                this.cmt_count = cmt_count;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setSeller_shop_site(String seller_shop_site) {
                this.seller_shop_site = seller_shop_site;
            }

            public void setGoods_brief(String goods_brief) {
                this.goods_brief = goods_brief;
            }

            public void setGoods_click(String goods_click) {
                this.goods_click = goods_click;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public void setShipping_fee(String shipping_fee) {
                this.shipping_fee = shipping_fee;
            }

            public void setIs_collect(int is_collect) {
                this.is_collect = is_collect;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public void setAlbum(List<String> album) {
                this.album = album;
            }

            public String getTao_pwd() {
                return tao_pwd;
            }

            public String getCat_id() {
                return cat_id;
            }

            public String getGoods_collect() {
                return goods_collect;
            }

            public String getAdd_time() {
                return add_time;
            }

            public String getShop_price() {
                return shop_price;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public String getIs_coupon() {
                return is_coupon;
            }

            public String getCmt_count() {
                return cmt_count;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getSeller_shop_site() {
                return seller_shop_site;
            }

            public String getGoods_brief() {
                return goods_brief;
            }

            public String getGoods_click() {
                return goods_click;
            }

            public String getMarket_price() {
                return market_price;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public String getShipping_fee() {
                return shipping_fee;
            }

            public int getIs_collect() {
                return is_collect;
            }

            public String getShare_url() {
                return share_url;
            }

            public List<String> getAlbum() {
                return album;
            }
        }
    }
}
