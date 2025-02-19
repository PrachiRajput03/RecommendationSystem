import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;


public class recommendationSystem {
    public static void main(String[] args) {
        try{
            // Loading the dataset from the resources file
            DataModel model = new FileDataModel(new File("dataset.csv"));

            // Create a similarity algorithm
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Creating a user neighborhood
            UserNeighborhood neighborhood = new NearestUserNeighborhood(2, similarity, model);

            // Creating a recommender 
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Getting recommendations for user
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);

            // Printing recommendations for user
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Recommended Ite,: " + recommendation.getItemID() + "Score: " + recommendation.getValue());
            }
        } catch (Exception e) {
            System.out.println("There was an error.");
            e.printStackTrace();
        }
    }
}



