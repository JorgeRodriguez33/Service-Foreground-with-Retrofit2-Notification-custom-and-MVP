package com.example.servicewhitretrofit2.Models;

import java.util.List;
import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Generate with https://www.jsonschema2pojo.org/
    @Generated("jsonschema2pojo")
    public class DatumWeather {

        @SerializedName("app_temp")
        @Expose
        private Double appTemp;
        @SerializedName("aqi")
        @Expose
        private Integer aqi;
        @SerializedName("city_name")
        @Expose
        private String cityName;
        @SerializedName("clouds")
        @Expose
        private Integer clouds;
        @SerializedName("country_code")
        @Expose
        private String countryCode;
        @SerializedName("datetime")
        @Expose
        private String datetime;
        @SerializedName("dewpt")
        @Expose
        private Double dewpt;
        @SerializedName("dhi")
        @Expose
        private Double dhi;
        @SerializedName("dni")
        @Expose
        private Double dni;
        @SerializedName("elev_angle")
        @Expose
        private Double elevAngle;
        @SerializedName("ghi")
        @Expose
        private Double ghi;
        @SerializedName("gust")
        @Expose
        private Double gust;
        @SerializedName("h_angle")
        @Expose
        private double hAngle;
        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lon")
        @Expose
        private Double lon;
        @SerializedName("ob_time")
        @Expose
        private String obTime;
        @SerializedName("pod")
        @Expose
        private String pod;
        @SerializedName("precip")
        @Expose
        private double precip;
        @SerializedName("pres")
        @Expose
        private Double pres;
        @SerializedName("rh")
        @Expose
        private Integer rh;
        @SerializedName("slp")
        @Expose
        private double slp;
        @SerializedName("snow")
        @Expose
        private Integer snow;
        @SerializedName("solar_rad")
        @Expose
        private Double solarRad;
        @SerializedName("sources")
        @Expose
        private List<String> sources = null;
        @SerializedName("state_code")
        @Expose
        private String stateCode;
        @SerializedName("station")
        @Expose
        private String station;
        @SerializedName("sunrise")
        @Expose
        private String sunrise;
        @SerializedName("sunset")
        @Expose
        private String sunset;
        @SerializedName("temp")
        @Expose
        private Double temp;
        @SerializedName("timezone")
        @Expose
        private String timezone;
        @SerializedName("ts")
        @Expose
        private Integer ts;
        @SerializedName("uv")
        @Expose
        private Double uv;
        @SerializedName("vis")
        @Expose
        private Integer vis;
        @SerializedName("weather")
        @Expose
        private Weather weather;
        @SerializedName("wind_cdir")
        @Expose
        private String windCdir;
        @SerializedName("wind_cdir_full")
        @Expose
        private String windCdirFull;
        @SerializedName("wind_dir")
        @Expose
        private Integer windDir;
        @SerializedName("wind_spd")
        @Expose
        private Double windSpd;

        public Double getAppTemp() {
            return appTemp;
        }

        public void setAppTemp(Double appTemp) {
            this.appTemp = appTemp;
        }

        public Integer getAqi() {
            return aqi;
        }

        public void setAqi(Integer aqi) {
            this.aqi = aqi;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public Integer getClouds() {
            return clouds;
        }

        public void setClouds(Integer clouds) {
            this.clouds = clouds;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public Double getDewpt() {
            return dewpt;
        }

        public void setDewpt(Double dewpt) {
            this.dewpt = dewpt;
        }

        public Double getDhi() {
            return dhi;
        }

        public void setDhi(Double dhi) {
            this.dhi = dhi;
        }

        public Double getDni() {
            return dni;
        }

        public void setDni(Double dni) {
            this.dni = dni;
        }

        public Double getElevAngle() {
            return elevAngle;
        }

        public void setElevAngle(Double elevAngle) {
            this.elevAngle = elevAngle;
        }

        public Double getGhi() {
            return ghi;
        }

        public void setGhi(Double ghi) {
            this.ghi = ghi;
        }

        public Double getGust() {
            return gust;
        }

        public void setGust(Double gust) {
            this.gust = gust;
        }

        public double gethAngle() {
            return hAngle;
        }

        public void sethAngle(double hAngle) {
            this.hAngle = hAngle;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

        public String getObTime() {
            return obTime;
        }

        public void setObTime(String obTime) {
            this.obTime = obTime;
        }

        public String getPod() {
            return pod;
        }

        public void setPod(String pod) {
            this.pod = pod;
        }

        public double getPrecip() {
            return precip;
        }

        public void setPrecip(double precip) {
            this.precip = precip;
        }

        public Double getPres() {
            return pres;
        }

        public void setPres(Double pres) {
            this.pres = pres;
        }

        public Integer getRh() {
            return rh;
        }

        public void setRh(Integer rh) {
            this.rh = rh;
        }

        public double getSlp() {
            return slp;
        }

        public void setSlp(double slp) {
            this.slp = slp;
        }

        public Integer getSnow() {
            return snow;
        }

        public void setSnow(Integer snow) {
            this.snow = snow;
        }

        public Double getSolarRad() {
            return solarRad;
        }

        public void setSolarRad(Double solarRad) {
            this.solarRad = solarRad;
        }

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

        public String getStation() {
            return station;
        }

        public void setStation(String station) {
            this.station = station;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public Double getTemp() {
            return temp;
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public Integer getTs() {
            return ts;
        }

        public void setTs(Integer ts) {
            this.ts = ts;
        }

        public Double getUv() {
            return uv;
        }

        public void setUv(Double uv) {
            this.uv = uv;
        }

        public Integer getVis() {
            return vis;
        }

        public void setVis(Integer vis) {
            this.vis = vis;
        }

        public Weather getWeather() {
            return weather;
        }

        public void setWeather(Weather weather) {
            this.weather = weather;
        }

        public String getWindCdir() {
            return windCdir;
        }

        public void setWindCdir(String windCdir) {
            this.windCdir = windCdir;
        }

        public String getWindCdirFull() {
            return windCdirFull;
        }

        public void setWindCdirFull(String windCdirFull) {
            this.windCdirFull = windCdirFull;
        }

        public Integer getWindDir() {
            return windDir;
        }

        public void setWindDir(Integer windDir) {
            this.windDir = windDir;
        }

        public Double getWindSpd() {
            return windSpd;
        }

        public void setWindSpd(Double windSpd) {
            this.windSpd = windSpd;
        }


}
