package com.codingmankk.www.retrofitsimpleone.Bean;

/**
 * ================================================
 * 版    本：
 * 创建日期：
 * 描    述：
 * 修订历史：
 * ================================================
 */

// URL模板---http://fy.iciba.com/ajax.php
// URL实例---http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
// 参数说明：
// a：固定值 fy
// f：原文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
// t：译文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
// w：查询内容



    /*{
            "status": 1,            //请求成功：取1
            "content": {            //内容信息
                 "from": "en-EU",        //原文内容类型
                 "to": "zh-CN",          //译文内容类型
                 "vendor": "baidu",      //来源平台
                 "out": "你好世界",       //译文内容
                 "err_no": 0             //请求成功时取0
            }
        }
    */

public class Translation {

    public int status;
    public Content content;

    public static class Content{
        String from;
        String to;
        String vendor;
        String out;
        String err_no;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getOut() {
            return out;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public String getErr_no() {
            return err_no;
        }

        public void setErr_no(String err_no) {
            this.err_no = err_no;
        }

        @Override
        public String toString() {
            return "Content{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", vendor='" + vendor + '\'' +
                    ", out='" + out + '\'' +
                    ", err_no='" + err_no + '\'' +
                    '}';
        }
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Translation{" +
                "status=" + status +
                ", content=" + content +
                '}';
    }
}
