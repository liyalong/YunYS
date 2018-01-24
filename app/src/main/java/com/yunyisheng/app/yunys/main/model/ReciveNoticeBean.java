package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/21 17:42
 * 邮箱：duoendeavor@163.com
 * 用途：我发送的的公告bean
 */

public class ReciveNoticeBean extends BaseModel {


    /**
     * list : [{"receiveStatus":"1","announcement":{"announcementId":8,"createUserId":"130","createTime":"2018-1-8 12:16:01","title":"标题","content":"内容","isDelete":1}}]
     * total : 1
     */

    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * receiveStatus : 1
         * announcement : {"announcementId":8,"createUserId":"130","createTime":"2018-1-8 12:16:01","title":"标题","content":"内容","isDelete":1}
         */

        private String receiveStatus;
        private AnnouncementBean announcement;

        public String getReceiveStatus() {
            return receiveStatus;
        }

        public void setReceiveStatus(String receiveStatus) {
            this.receiveStatus = receiveStatus;
        }

        public AnnouncementBean getAnnouncement() {
            return announcement;
        }

        public void setAnnouncement(AnnouncementBean announcement) {
            this.announcement = announcement;
        }

        public static class AnnouncementBean {
            /**
             * announcementId : 8
             * createUserId : 130
             * createTime : 2018-1-8 12:16:01
             * title : 标题
             * content : 内容
             * isDelete : 1
             */

            private int announcementId;
            private String createUserId;
            private String createTime;
            private String title;
            private String content;
            private int isDelete;

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

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }
        }
    }
}
