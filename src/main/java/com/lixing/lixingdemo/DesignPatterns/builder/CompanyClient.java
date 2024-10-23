package com.lixing.lixingdemo.DesignPatterns.builder;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-11-18 14:09
 */
public class CompanyClient {
    public final String companyName;
    public final String companyAddress;
    public final double companyRegfunds;
    public final String mPerson;
    public final String mType;

    public CompanyClient() {
        this(new Builder());
    }

    public CompanyClient(Builder builder) {
        this.companyName = builder.companyName;
        this.companyAddress = builder.companyAddress;
        this.companyRegfunds = builder.companyRegfunds;
        this.mPerson = builder.mPerson;
        this.mType = builder.mType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public double getCompanyRegfunds() {
        return companyRegfunds;
    }

    public String getmPerson() {
        return mPerson;
    }

    public String getmType() {
        return mType;
    }

    @Override
    public String toString() {
        return "CompanyClient{" +
                "companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyRegfunds=" + companyRegfunds +
                ", mPerson='" + mPerson + '\'' +
                ", mType='" + mType + '\'' +
                '}';
    }

    public static class Builder {
        public String companyName;
        public String companyAddress;
        public double companyRegfunds;
        public String mPerson;
        public String mType;

        public Builder() {
            this.companyName = companyName;
            this.companyAddress = companyAddress;
            this.companyRegfunds = companyRegfunds;
            this.mPerson = mPerson;
            this.mType = mType;
        }

        Builder(CompanyClient client) {
            this.companyName= client.companyName;
            this.companyAddress = client.companyAddress;
            this.companyRegfunds = client.companyRegfunds;
            this.mPerson = client.mPerson;
            this.mType = client.mType;
        }

        public Builder setCompanyName(String name) {
            companyName = name;
            return this;
        }

        public Builder setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
            return this;
        }

        public Builder setCompanyRegfunds(double companyRegfunds) {
            this.companyRegfunds = companyRegfunds;
            return this;
        }

        public Builder setmPerson(String mPerson) {
            this.mPerson = mPerson;
            return this;
        }

        public Builder setmType(String mType) {
            this.mType = mType;
            return this;
        }

        public CompanyClient build(){
            return new CompanyClient(this);
        }
    }
}
