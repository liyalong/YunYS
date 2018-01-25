package com.yunyisheng.app.yunys.main.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/1/24 15:12
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class FromworkBean extends BaseModel {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 2
         * text : 友友
         * children : true
         * sectionParentid : 0
         * users : [{"name":"小明","userId":130,"icon":null,"userPhone":"13635706239","userMailbox":null,"userSex":null,"userIsShow":true}]
         * subdivision : [{"id":3,"text":"财务部","children":false,"sectionParentid":2,"users":[{"name":"小白","userId":131,"icon":null,"userPhone":"13635706239","userMailbox":null,"userSex":null,"userIsShow":true}],"subdivision":null,"type":"section"},{"id":4,"text":"研发部","children":true,"sectionParentid":2,"users":[],"subdivision":[{"id":11,"text":"研发C","children":true,"sectionParentid":4,"users":[{"name":"小a3","userId":139,"icon":null,"userPhone":"13635706239","userMailbox":null,"userSex":null,"userIsShow":true}],"subdivision":null,"type":"section"},{"id":12,"text":"研发B","children":false,"sectionParentid":4,"users":[],"subdivision":null,"type":"section"}],"type":"section"},{"id":5,"text":"市场部","children":true,"sectionParentid":2,"users":[],"subdivision":[{"id":6,"text":"财务A","children":true,"sectionParentid":5,"users":[{"name":"小白4","userId":134,"icon":null,"userPhone":"13635706239","userMailbox":null,"userSex":null,"userIsShow":true}],"subdivision":[{"id":7,"text":"财务B","children":false,"sectionParentid":6,"users":[{"name":"小白5","userId":135,"icon":null,"userPhone":"13635706239","userMailbox":null,"userSex":null,"userIsShow":true}],"subdivision":null,"type":"section"},{"id":8,"text":"财务A-1","children":true,"sectionParentid":6,"users":[{"name":"小白6","userId":136,"icon":null,"userPhone":"13635706239","userMailbox":null,"userSex":null,"userIsShow":true}],"subdivision":[{"id":9,"text":"财务1","children":false,"sectionParentid":8,"users":[{"name":"小a1","userId":137,"icon":null,"userPhone":"13635706239","userMailbox":null,"userSex":null,"userIsShow":true}],"subdivision":null,"type":"section"}],"type":"section"}],"type":"section"}],"type":"section"},{"id":19,"text":"财务二部","children":true,"sectionParentid":2,"users":[],"subdivision":null,"type":"section"}]
         * type : section
         */

        private int id;
        private String text;
        private boolean children;
        private int sectionParentid;
        private String type;
        private List<UsersBean> users;
        private List<SubdivisionBean> subdivision;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isChildren() {
            return children;
        }

        public void setChildren(boolean children) {
            this.children = children;
        }

        public int getSectionParentid() {
            return sectionParentid;
        }

        public void setSectionParentid(int sectionParentid) {
            this.sectionParentid = sectionParentid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public List<SubdivisionBean> getSubdivision() {
            return subdivision;
        }

        public void setSubdivision(List<SubdivisionBean> subdivision) {
            this.subdivision = subdivision;
        }

        public static class UsersBean {
            /**
             * name : 小明
             * userId : 130
             * icon : null
             * userPhone : 13635706239
             * userMailbox : null
             * userSex : null
             * userIsShow : true
             */

            private String name;
            private int userId;
            private String icon;
            private String userPhone;
            private String userMailbox;
            private Object userSex;
            private boolean userIsShow;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getUserMailbox() {
                return userMailbox;
            }

            public void setUserMailbox(String userMailbox) {
                this.userMailbox = userMailbox;
            }

            public Object getUserSex() {
                return userSex;
            }

            public void setUserSex(Object userSex) {
                this.userSex = userSex;
            }

            public boolean isUserIsShow() {
                return userIsShow;
            }

            public void setUserIsShow(boolean userIsShow) {
                this.userIsShow = userIsShow;
            }
        }

        public static class SubdivisionBean {
            /**
             * id : 3
             * text : 财务部
             * children : false
             * sectionParentid : 2
             * users : [{"name":"小白","userId":131,"icon":null,"userPhone":"13635706239","userMailbox":null,"userSex":null,"userIsShow":true}]
             * subdivision : null
             * type : section
             */

            private int id;
            private String text;
            private boolean children;
            private int sectionParentid;
            private Object subdivision;
            private String type;
            private List<UsersBeanX> users;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public boolean isChildren() {
                return children;
            }

            public void setChildren(boolean children) {
                this.children = children;
            }

            public int getSectionParentid() {
                return sectionParentid;
            }

            public void setSectionParentid(int sectionParentid) {
                this.sectionParentid = sectionParentid;
            }

            public Object getSubdivision() {
                return subdivision;
            }

            public void setSubdivision(Object subdivision) {
                this.subdivision = subdivision;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<UsersBeanX> getUsers() {
                return users;
            }

            public void setUsers(List<UsersBeanX> users) {
                this.users = users;
            }

            public static class UsersBeanX {
                /**
                 * name : 小白
                 * userId : 131
                 * icon : null
                 * userPhone : 13635706239
                 * userMailbox : null
                 * userSex : null
                 * userIsShow : true
                 */

                private String name;
                private int userId;
                private Object icon;
                private String userPhone;
                private Object userMailbox;
                private Object userSex;
                private boolean userIsShow;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public Object getIcon() {
                    return icon;
                }

                public void setIcon(Object icon) {
                    this.icon = icon;
                }

                public String getUserPhone() {
                    return userPhone;
                }

                public void setUserPhone(String userPhone) {
                    this.userPhone = userPhone;
                }

                public Object getUserMailbox() {
                    return userMailbox;
                }

                public void setUserMailbox(Object userMailbox) {
                    this.userMailbox = userMailbox;
                }

                public Object getUserSex() {
                    return userSex;
                }

                public void setUserSex(Object userSex) {
                    this.userSex = userSex;
                }

                public boolean isUserIsShow() {
                    return userIsShow;
                }

                public void setUserIsShow(boolean userIsShow) {
                    this.userIsShow = userIsShow;
                }
            }
        }
    }
}
