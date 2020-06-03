package com.example.administrator.duotu;

import java.util.List;

public class Beanshuiguo {

    /**
     * result : success
     * code : 200
     * data : [{"cpmc":"越南青芒","cpbh":"201912182018097416"},{"cpmc":"糖心苹果","cpbh":"201912182023018773"},{"cpmc":"猕猴桃","cpbh":"201912182020259702"},{"cpmc":"柚子","cpbh":"201912182025202170"},{"cpmc":"榴莲","cpbh":"201912182027061905"},{"cpmc":"京欣西瓜","cpbh":"201912182031334688"},{"cpmc":"柿子","cpbh":"201912182021277351"},{"cpmc":"红富士苹果","cpbh":"201912182019189631"},{"cpmc":"巨峰葡萄","cpbh":"201912182030395535"},{"cpmc":"测试产品","cpbh":"201912171131073719"},{"cpmc":"夏季水果","cpbh":"201912182034206435"},{"cpmc":"芹菜","cpbh":"201912182029298351"}]
     */

    private String result;
    private int code;
    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cpmc : 越南青芒
         * cpbh : 201912182018097416
         */

        private String cpmc;
        private String cpbh;

        public String getCpmc() {
            return cpmc;
        }

        public void setCpmc(String cpmc) {
            this.cpmc = cpmc;
        }

        public String getCpbh() {
            return cpbh;
        }

        public void setCpbh(String cpbh) {
            this.cpbh = cpbh;
        }
    }
}
