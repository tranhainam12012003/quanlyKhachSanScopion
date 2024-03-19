package vn.teca.scopio.base.model.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private String userId;
    private String userName;
    private String userCode;
    private Number specInitId;
    private String userType;
    private String userUsingLevel;
    private String specUnitName;
    private String mobile;
    private String position;
    private String email;
    private String userLevel;
    private Integer doubleGate;
    private String areaCode;
    private String areaName;
    private String jobCode;
    private String jobName;

    private Number cityId;
    private Number districtId;
    private Number villageId;
    private boolean island = false;

    private String cityCode;
    private String districtCode;
    private String villageCode;
    private String specUnitCode;
    private String emailAddress;
    private List<String> listTask;
    private List<String> listUrl;

    private Set<String> authorities;

    private String specUnitParentName;

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Integer getDoubleGate() {
        if(doubleGate == null){
            doubleGate = 0;
        }
        return doubleGate;
    }

    public void setDoubleGate(Integer doubleGate) {
        this.doubleGate = doubleGate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public boolean isIsland() {
        return island;
    }

    public String getSpecUnitCode() {
        return specUnitCode;
    }

    public void setSpecUnitCode(String specUnitCode) {
        this.specUnitCode = specUnitCode;
    }

    public boolean getIsland() {
        return island;
    }

    public void setIsland(boolean island) {
        this.island = island;
    }

    public String getSpecUnitParentName() {
        return specUnitParentName;
    }



    public List<String> getListUrl() {
        return listUrl;
    }



    public void setListUrl(List<String> listUrl) {
        this.listUrl = listUrl;
    }



    public void setSpecUnitParentName(String specUnitParentName) {
        this.specUnitParentName = specUnitParentName;
    }



    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getUserUsingLevel() {
        return userUsingLevel;
    }

    public void setUserUsingLevel(String userUsingLevel) {
        this.userUsingLevel = userUsingLevel;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        if (!StringUtils.isEmpty(userName) && userName.indexOf("\n") > -1) {
            return userName.replaceAll("\n", "");
        }
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Number getSpecInitId() {
        return specInitId;
    }

    public void setSpecInitId(Number specInitId) {
        this.specInitId = specInitId;
    }

    public String getSpecUnitName() {
        return specUnitName;
    }

    public void setSpecUnitName( String specUnitName ) {
        this.specUnitName = specUnitName;
    }

    public Number getCityId() {
        return cityId;
    }

    public void setCityId(Number cityId) {
        this.cityId = cityId;
    }

    public Number getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Number districtId) {
        this.districtId = districtId;
    }

    public Number getVillageId() {
        return villageId;
    }

    public void setVillageId(Number villageId) {
        this.villageId = villageId;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<String> getListTask() {
        return listTask;
    }

    public void setListTask(List<String> listTask) {
        this.listTask = listTask;
    }
}

