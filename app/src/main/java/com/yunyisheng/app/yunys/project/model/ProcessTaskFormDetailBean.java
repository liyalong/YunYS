package com.yunyisheng.app.yunys.project.model;

import com.yunyisheng.app.yunys.base.BaseModel;

import java.util.List;

/**
 * 作者：fuduo on 2018/2/5 17:32
 * 邮箱：duoendeavor@163.com
 * 用途：
 */

public class ProcessTaskFormDetailBean extends BaseModel {


    /**
     * respBody : {"version":2,"baseId":116,"typeId":41,"applicationId":"706311a4101d4d409c264b451c8d4672","companyId":"88d3c7fccd154c66861621c45ed4d75e","name":"热热","image":"page/library/4.png","createUser":304,"createTime":"2018-01-28 22:27:32","uuid":"8afba00344d040fb96ec5aea7da9741a","fields":0,"description":"尔尔","explanation":"二","isPrint":"1","data":[{"id":478,"uuid":"8afba00344d040fb96ec5aea7da9741a","name":"data_1","leipiplugins":"text","type":null,"value":"云依生","readonly":null,"title":"文本框","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input style=\"text-align: left; width: 150px;\" title=\"文本框\" value=\"云依生\" name=\"data_1\" orgheight=\"\" orgwidth=\"150\" orgalign=\"left\" orgfontsize=\"\" orghide=\"0\" leipiplugins=\"text\" orgtype=\"text\"/>","orgfontsize":"","orgrich":null,"orgheight":"","src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":479,"uuid":"8afba00344d040fb96ec5aea7da9741a","name":"data_2","leipiplugins":"select","type":null,"value":"下拉,菜单","readonly":null,"title":"下拉菜单","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"width: 150px;","content":"<span leipiplugins=\"select\"><select name=\"data_2\" title=\"下拉菜单\" leipiplugins=\"select\" size=\"1\" orgwidth=\"150\" style=\"width: 150px;\"><option value=\"下拉\">下拉<\/option><option value=\"菜单\">菜单<\/option><\/select>&nbsp;&nbsp;<\/span>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":null,"optionStr":"[]","orgtype":null},{"id":480,"uuid":"8afba00344d040fb96ec5aea7da9741a","name":"data_3","leipiplugins":"radios","type":null,"value":"单选1,单选2","readonly":null,"title":"单选","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":"<span leipiplugins=\"radios\" name=\"data_3\" title=\"单选\"><input type=\"radio\" name=\"data_3\" value=\"单选1\"  checked=\"checked\"/>单选1&nbsp;<input type=\"radio\" name=\"data_3\" value=\"单选2\"  />单选2&nbsp;<\/span>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[{"value":"单选1","type":"radio","checked":"checked","name":"data_3"},{"value":"单选2","type":"radio","checked":null,"name":"data_3"}],"orgalign":null,"optionStr":"[{\"checked\":\"checked\",\"name\":\"data_3\",\"type\":\"radio\",\"value\":\"单选1\"},{\"name\":\"data_3\",\"type\":\"radio\",\"value\":\"单选2\"}]","orgtype":null},{"id":481,"uuid":"8afba00344d040fb96ec5aea7da9741a","name":"data_4,data_5,data_6","leipiplugins":"checkboxs","type":null,"value":"复选1,复选2,复选3","readonly":null,"title":"复选","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":"<span leipiplugins=\"checkboxs\"  title=\"复选\"><input type=\"checkbox\" name=\"data_4\" value=\"复选1\"  checked=\"checked\"/>复选1&nbsp;<input type=\"checkbox\" name=\"data_5\" value=\"复选2\"  checked=\"checked\"/>复选2&nbsp;<input type=\"checkbox\" name=\"data_6\" value=\"复选3\"  />复选3&nbsp;<\/span>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[{"value":"复选1","type":"checkbox","checked":"checked","name":"data_4"},{"value":"复选2","type":"checkbox","checked":"checked","name":"data_5"},{"value":"复选3","type":"checkbox","checked":null,"name":"data_6"}],"orgalign":null,"optionStr":"[{\"checked\":\"checked\",\"name\":\"data_4\",\"type\":\"checkbox\",\"value\":\"复选1\"},{\"checked\":\"checked\",\"name\":\"data_5\",\"type\":\"checkbox\",\"value\":\"复选2\"},{\"name\":\"data_6\",\"type\":\"checkbox\",\"value\":\"复选3\"}]","orgtype":null}]}
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
         * version : 2
         * baseId : 116
         * typeId : 41
         * applicationId : 706311a4101d4d409c264b451c8d4672
         * companyId : 88d3c7fccd154c66861621c45ed4d75e
         * name : 热热
         * image : page/library/4.png
         * createUser : 304
         * createTime : 2018-01-28 22:27:32
         * uuid : 8afba00344d040fb96ec5aea7da9741a
         * fields : 0
         * description : 尔尔
         * explanation : 二
         * isPrint : 1
         * data : [{"id":478,"uuid":"8afba00344d040fb96ec5aea7da9741a","name":"data_1","leipiplugins":"text","type":null,"value":"云依生","readonly":null,"title":"文本框","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"text-align: left; width: 150px;","content":"<input style=\"text-align: left; width: 150px;\" title=\"文本框\" value=\"云依生\" name=\"data_1\" orgheight=\"\" orgwidth=\"150\" orgalign=\"left\" orgfontsize=\"\" orghide=\"0\" leipiplugins=\"text\" orgtype=\"text\"/>","orgfontsize":"","orgrich":null,"orgheight":"","src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":"left","optionStr":"[]","orgtype":"text"},{"id":479,"uuid":"8afba00344d040fb96ec5aea7da9741a","name":"data_2","leipiplugins":"select","type":null,"value":"下拉,菜单","readonly":null,"title":"下拉菜单","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":"150","style":"width: 150px;","content":"<span leipiplugins=\"select\"><select name=\"data_2\" title=\"下拉菜单\" leipiplugins=\"select\" size=\"1\" orgwidth=\"150\" style=\"width: 150px;\"><option value=\"下拉\">下拉<\/option><option value=\"菜单\">菜单<\/option><\/select>&nbsp;&nbsp;<\/span>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[],"orgalign":null,"optionStr":"[]","orgtype":null},{"id":480,"uuid":"8afba00344d040fb96ec5aea7da9741a","name":"data_3","leipiplugins":"radios","type":null,"value":"单选1,单选2","readonly":null,"title":"单选","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":"<span leipiplugins=\"radios\" name=\"data_3\" title=\"单选\"><input type=\"radio\" name=\"data_3\" value=\"单选1\"  checked=\"checked\"/>单选1&nbsp;<input type=\"radio\" name=\"data_3\" value=\"单选2\"  />单选2&nbsp;<\/span>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[{"value":"单选1","type":"radio","checked":"checked","name":"data_3"},{"value":"单选2","type":"radio","checked":null,"name":"data_3"}],"orgalign":null,"optionStr":"[{\"checked\":\"checked\",\"name\":\"data_3\",\"type\":\"radio\",\"value\":\"单选1\"},{\"name\":\"data_3\",\"type\":\"radio\",\"value\":\"单选2\"}]","orgtype":null},{"id":481,"uuid":"8afba00344d040fb96ec5aea7da9741a","name":"data_4,data_5,data_6","leipiplugins":"checkboxs","type":null,"value":"复选1,复选2,复选3","readonly":null,"title":"复选","orgtitle":null,"orgcoltype":null,"orgunit":null,"orgsum":null,"orgcolvalue":null,"orgwidth":null,"style":null,"content":"<span leipiplugins=\"checkboxs\"  title=\"复选\"><input type=\"checkbox\" name=\"data_4\" value=\"复选1\"  checked=\"checked\"/>复选1&nbsp;<input type=\"checkbox\" name=\"data_5\" value=\"复选2\"  checked=\"checked\"/>复选2&nbsp;<input type=\"checkbox\" name=\"data_6\" value=\"复选3\"  />复选3&nbsp;<\/span>","orgfontsize":null,"orgrich":null,"orgheight":null,"src":null,"orgsigntype":null,"parseName":null,"options":[{"value":"复选1","type":"checkbox","checked":"checked","name":"data_4"},{"value":"复选2","type":"checkbox","checked":"checked","name":"data_5"},{"value":"复选3","type":"checkbox","checked":null,"name":"data_6"}],"orgalign":null,"optionStr":"[{\"checked\":\"checked\",\"name\":\"data_4\",\"type\":\"checkbox\",\"value\":\"复选1\"},{\"checked\":\"checked\",\"name\":\"data_5\",\"type\":\"checkbox\",\"value\":\"复选2\"},{\"name\":\"data_6\",\"type\":\"checkbox\",\"value\":\"复选3\"}]","orgtype":null}]
         */

        private int version;
        private int baseId;
        private int typeId;
        private String applicationId;
        private String companyId;
        private String name;
        private String image;
        private int createUser;
        private String createTime;
        private String uuid;
        private int fields;
        private String description;
        private String explanation;
        private String isPrint;
        private List<DataBean> data;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getBaseId() {
            return baseId;
        }

        public void setBaseId(int baseId) {
            this.baseId = baseId;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(String applicationId) {
            this.applicationId = applicationId;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getCreateUser() {
            return createUser;
        }

        public void setCreateUser(int createUser) {
            this.createUser = createUser;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public int getFields() {
            return fields;
        }

        public void setFields(int fields) {
            this.fields = fields;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getExplanation() {
            return explanation;
        }

        public void setExplanation(String explanation) {
            this.explanation = explanation;
        }

        public String getIsPrint() {
            return isPrint;
        }

        public void setIsPrint(String isPrint) {
            this.isPrint = isPrint;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 478
             * uuid : 8afba00344d040fb96ec5aea7da9741a
             * name : data_1
             * leipiplugins : text
             * type : null
             * value : 云依生
             * readonly : null
             * title : 文本框
             * orgtitle : null
             * orgcoltype : null
             * orgunit : null
             * orgsum : null
             * orgcolvalue : null
             * orgwidth : 150
             * style : text-align: left; width: 150px;
             * content : <input style="text-align: left; width: 150px;" title="文本框" value="云依生" name="data_1" orgheight="" orgwidth="150" orgalign="left" orgfontsize="" orghide="0" leipiplugins="text" orgtype="text"/>
             * orgfontsize :
             * orgrich : null
             * orgheight :
             * src : null
             * orgsigntype : null
             * parseName : null
             * options : []
             * orgalign : left
             * optionStr : []
             * orgtype : text
             */

            private int id;
            private String uuid;
            private String name;
            private String leipiplugins;
            private Object type;
            private String value;
            private Object readonly;
            private String title;
            private Object orgtitle;
            private Object orgcoltype;
            private Object orgunit;
            private Object orgsum;
            private Object orgcolvalue;
            private String orgwidth;
            private String style;
            private String content;
            private String orgfontsize;
            private Object orgrich;
            private String orgheight;
            private Object src;
            private Object orgsigntype;
            private Object parseName;
            private String orgalign;
            private String optionStr;
            private String orgtype;
            private List<opationValue> options;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLeipiplugins() {
                return leipiplugins;
            }

            public void setLeipiplugins(String leipiplugins) {
                this.leipiplugins = leipiplugins;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public Object getReadonly() {
                return readonly;
            }

            public void setReadonly(Object readonly) {
                this.readonly = readonly;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getOrgtitle() {
                return orgtitle;
            }

            public void setOrgtitle(Object orgtitle) {
                this.orgtitle = orgtitle;
            }

            public Object getOrgcoltype() {
                return orgcoltype;
            }

            public void setOrgcoltype(Object orgcoltype) {
                this.orgcoltype = orgcoltype;
            }

            public Object getOrgunit() {
                return orgunit;
            }

            public void setOrgunit(Object orgunit) {
                this.orgunit = orgunit;
            }

            public Object getOrgsum() {
                return orgsum;
            }

            public void setOrgsum(Object orgsum) {
                this.orgsum = orgsum;
            }

            public Object getOrgcolvalue() {
                return orgcolvalue;
            }

            public void setOrgcolvalue(Object orgcolvalue) {
                this.orgcolvalue = orgcolvalue;
            }

            public String getOrgwidth() {
                return orgwidth;
            }

            public void setOrgwidth(String orgwidth) {
                this.orgwidth = orgwidth;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getOrgfontsize() {
                return orgfontsize;
            }

            public void setOrgfontsize(String orgfontsize) {
                this.orgfontsize = orgfontsize;
            }

            public Object getOrgrich() {
                return orgrich;
            }

            public void setOrgrich(Object orgrich) {
                this.orgrich = orgrich;
            }

            public String getOrgheight() {
                return orgheight;
            }

            public void setOrgheight(String orgheight) {
                this.orgheight = orgheight;
            }

            public Object getSrc() {
                return src;
            }

            public void setSrc(Object src) {
                this.src = src;
            }

            public Object getOrgsigntype() {
                return orgsigntype;
            }

            public void setOrgsigntype(Object orgsigntype) {
                this.orgsigntype = orgsigntype;
            }

            public Object getParseName() {
                return parseName;
            }

            public void setParseName(Object parseName) {
                this.parseName = parseName;
            }

            public String getOrgalign() {
                return orgalign;
            }

            public void setOrgalign(String orgalign) {
                this.orgalign = orgalign;
            }

            public String getOptionStr() {
                return optionStr;
            }

            public void setOptionStr(String optionStr) {
                this.optionStr = optionStr;
            }

            public String getOrgtype() {
                return orgtype;
            }

            public void setOrgtype(String orgtype) {
                this.orgtype = orgtype;
            }

            public List<opationValue> getOptions() {
                return options;
            }

            public void setOptions(List<opationValue> options) {
                this.options = options;
            }
        }

        public static class opationValue{

            /**
             * value : 单选1
             * type : radio
             * checked : checked
             * name : data_3
             */

            private String value;
            private String type;
            private String checked;
            private String name;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getChecked() {
                return checked;
            }

            public void setChecked(String checked) {
                this.checked = checked;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

    }
}
