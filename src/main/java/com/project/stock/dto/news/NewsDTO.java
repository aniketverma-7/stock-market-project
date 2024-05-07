package com.project.stock.dto.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NewsDTO {
    private int items;
    @JsonProperty("sentiment_score_definition")
    private String sentimentScoreDefinition;
    @JsonProperty("relevance_score_definition")
    private String relevanceScoreDefinition;

    private List<Feed> feed;
    @Data
    public static class Feed {
        private String title;

        private String url;

        @JsonProperty("time_published")
        private String timePublished;

        private List<String> authors;

        private String summary;

        @JsonProperty("banner_image")
        private String bannerImage;

        private String source;

        @JsonProperty("category_within_source")
        private String categoryWithinSource;

        @JsonProperty("sourceDomain")
        private String sourceDomain;

        private List<Topic> topics;

        @JsonProperty("overall_sentiment_score")
        private double overallSentimentScore;

        @JsonProperty("overall_sentiment_label")
        private String overallSentimentLabel;

        @JsonProperty("ticker_sentiment")
        private List<TickerSentiment> tickerSentiment;
        @Data
        public static class Topic {
            private String topic;

            @JsonProperty("relevance_score")
            private double relevanceScore;
        }
        @Data
        public static class TickerSentiment {
            private String ticker;

            @JsonProperty("relevance_score")
            private double relevanceScore;

            @JsonProperty("ticker_sentiment_score")
            private double tickerSentimentScore;

            @JsonProperty("ticker_sentiment_label")
            private String tickerSentimentLabel;
        }
    }
}
