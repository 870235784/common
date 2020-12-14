#### 1.fastjson, jackson序列化
```
    /**
     * 判断是否成功
     * @return
     *
     * 必须加上 @JSONField(serialize = false) @JsonIgnore
     * fastjson和jackson在序列化对象时, 默认会解析getXxx 和isXxx 方法
     * 使用 @JSONField(serialize = false) , fastjson解析时忽略该属性
     * 使用 @JsonIgnore, jackson在序列化对象时会忽略该属性
     * 对于非属性字段, 不建议使用 isXxx 和 getXxx 方法
     */
    @JSONField(serialize = false)
    @JsonIgnore
    public boolean isSuccess(){
        return CommonResultEnum.SUCCESS.getCode().equals(this.code);
    }

```