package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/2/6 22:04
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ReceiveMeMessageBean extends BaseModel {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * announcementId : 33
         * infoId : 105
         * receiveUserId : 307
         * receiveStatus : 1
         * readTime : null
         * enterpriseId : 88d3c7fccd154c66861621c45ed4d75e
         * platformId : 706311a4101d4d409c264b451c8d4672
         * isDelete : false
         * announcement : {"announcementId":33,"createUserId":null,"createUserName":"冀飞虎","createTime":"2017-10-22 14:24:52","title":"发公告","content":"132","isDelete":null,"enterpriseId":null,"platformId":null,"annexList":null,"receiverList":null,"readStatistic":null,"receiveInfoList":null}
         */

        private int announcementId;
        private int infoId;
        private int receiveUserId;
        private int receiveStatus;
        private String readTime;
        private String enterpriseId;
        private String platformId;
        private boolean isDelete;
        private AnnouncementBean announcement;

        public int getAnnouncementId() {
            return announcementId;
        }

        public void setAnnouncementId(int announcementId) {
            this.announcementId = announcementId;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public int getReceiveUserId() {
            return receiveUserId;
        }

        public void setReceiveUserId(int receiveUserId) {
            this.receiveUserId = receiveUserId;
        }

        public int getReceiveStatus() {
            return receiveStatus;
        }

        public void setReceiveStatus(int receiveStatus) {
            this.receiveStatus = receiveStatus;
        }

        public String getReadTime() {
            return readTime;
        }

        public void setReadTime(String readTime) {
            this.readTime = readTime;
        }

        public String getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(String enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public String getPlatformId() {
            return platformId;
        }

        public void setPlatformId(String platformId) {
            this.platformId = platformId;
        }

        public boolean isIsDelete() {
            return isDelete;
        }

        public void setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        public AnnouncementBean getAnnouncement() {
            return announcement;
        }

        public void setAnnouncement(AnnouncementBean announcement) {
            this.announcement = announcement;
        }

        public static class AnnouncementBean {
            /**
             * announcementId : 33
             * createUserId : null
             * createUserName : 冀飞虎
             * createTime : 2017-10-22 14:24:52
             * title : 发公告
             * content : 132
             * isDelete : null
             * enterpriseId : null
             * platformId : null
             * annexList : null
             * receiverList : null
             * readStatistic : null
             * receiveInfoList : null
             */

            private int announcementId;
            private Object createUserId;
            private String createUserName;
            private String createTime;
            private String title;
            private String content;
            private Object isDelete;
            private Object enterpriseId;
            private Object platformId;
            private Object annexList;
            private Object receiverList;
            private Object readStatistic;
            private Object receiveInfoList;

            public int getAnnouncementId() {
                return announcementId;
            }

            public void setAnnouncementId(int announcementId) {
                this.announcementId = announcementId;
            }

            public Object getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(Object createUserId) {
                this.createUserId = createUserId;
            }

            public String getCreateUserName() {
                return createUserName;
            }

            public void setCreateUserName(String createUserName) {
                this.createUserName = createUserName;
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

            public Object getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(Object isDelete) {
                this.isDelete = isDelete;
            }

            public Object getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(Object enterpriseId) {
                this.enterpriseId = enterpriseId;
            }

            public Object getPlatformId() {
                return platformId;
            }

            public void setPlatformId(Object platformId) {
                this.platformId = platformId;
            }

            public Object getAnnexList() {
                return annexList;
            }

            public void setAnnexList(Object annexList) {
                this.annexList = annexList;
            }

            public Object getReceiverList() {
                return receiverList;
            }

            public void setReceiverList(Object receiverList) {
                this.receiverList = receiverList;
            }

            public Object getReadStatistic() {
                return readStatistic;
            }

            public void setReadStatistic(Object readStatistic) {
                this.readStatistic = readStatistic;
            }

            public Object getReceiveInfoList() {
                return receiveInfoList;
            }

            public void setReceiveInfoList(Object receiveInfoList) {
                this.receiveInfoList = receiveInfoList;
            }
        }
    }
}
