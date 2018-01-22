package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/21 16:55
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class SendNoticeBean extends BaseModel {


    /**
     * respCode : 0
     * respMsg :
     * list : [{"announcementId":8,"createUserId":"130","createTime":"2018-1-8 12:16:01","title":"标题","content":"内容","isDelete":"1","readStatistic":"10/20"}]
     * total : 1
     */

    private List<ListBean> list;

    @Override
    public String toString() {
        return "SendNoticeBean{" +
                "list=" + list +
                '}';
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * announcementId : 8
         * createUserId : 130
         * createTime : 2018-1-8 12:16:01
         * title : 标题
         * content : 内容
         * isDelete : 1
         * readStatistic : 10/20
         */

        private int announcementId;
        private String createUserId;
        private String createTime;
        private String title;
        private String content;
        private String isDelete;
        private String readStatistic;

        @Override
        public String toString() {
            return "ListBean{" +
                    "announcementId=" + announcementId +
                    ", createUserId='" + createUserId + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", isDelete='" + isDelete + '\'' +
                    ", readStatistic='" + readStatistic + '\'' +
                    '}';
        }

        public int getAnnouncementId() {
            return announcementId;
        }

        public void setAnnouncementId(int announcementId) {
            this.announcementId = announcementId;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getReadStatistic() {
            return readStatistic;
        }

        public void setReadStatistic(String readStatistic) {
            this.readStatistic = readStatistic;
        }
    }
}
