package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/21 17:46
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class NoticeDetailBean extends BaseModel {

    /**
     * respBody : {"isDelete":false,"content":"内容d","createTime":"2018-01-08 04:24:50","title":"标题a","createUserName":null,"receiverList":[{"receiverName":"小a4","announcementId":9,"announcementReceiveId":13,"receiverId":"140","receiveType":1},{"receiverName":"小张4","announcementId":9,"announcementReceiveId":14,"receiverId":"150","receiveType":1}],"annexList":[3]}
     */

    private RespBodyBean respBody;

    public RespBodyBean getRespBody() {
        return respBody;
    }

    public void setRespBody(RespBodyBean respBody) {
        this.respBody = respBody;
    }

    public static class RespBodyBean {
        /**
         * isDelete : false
         * content : 内容d
         * createTime : 2018-01-08 04:24:50
         * title : 标题a
         * createUserName : null
         * receiverList : [{"receiverName":"小a4","announcementId":9,"announcementReceiveId":13,"receiverId":"140","receiveType":1},{"receiverName":"小张4","announcementId":9,"announcementReceiveId":14,"receiverId":"150","receiveType":1}]
         * annexList : [3]
         */

        private boolean isDelete;
        private String content;
        private String createTime;
        private String title;
        private String createUserName;
        private List<ReceiverListBean> receiverList;
        private List<Integer> annexList;

        public boolean isIsDelete() {
            return isDelete;
        }

        public void setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public List<ReceiverListBean> getReceiverList() {
            return receiverList;
        }

        public void setReceiverList(List<ReceiverListBean> receiverList) {
            this.receiverList = receiverList;
        }

        public List<Integer> getAnnexList() {
            return annexList;
        }

        public void setAnnexList(List<Integer> annexList) {
            this.annexList = annexList;
        }

        public static class ReceiverListBean {
            /**
             * receiverName : 小a4
             * announcementId : 9
             * announcementReceiveId : 13
             * receiverId : 140
             * receiveType : 1
             */

            private String receiverName;
            private int announcementId;
            private int announcementReceiveId;
            private String receiverId;
            private int receiveType;

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public int getAnnouncementId() {
                return announcementId;
            }

            public void setAnnouncementId(int announcementId) {
                this.announcementId = announcementId;
            }

            public int getAnnouncementReceiveId() {
                return announcementReceiveId;
            }

            public void setAnnouncementReceiveId(int announcementReceiveId) {
                this.announcementReceiveId = announcementReceiveId;
            }

            public String getReceiverId() {
                return receiverId;
            }

            public void setReceiverId(String receiverId) {
                this.receiverId = receiverId;
            }

            public int getReceiveType() {
                return receiveType;
            }

            public void setReceiveType(int receiveType) {
                this.receiveType = receiveType;
            }
        }
    }
}
