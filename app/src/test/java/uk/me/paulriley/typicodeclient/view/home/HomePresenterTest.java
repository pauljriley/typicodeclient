package uk.me.paulriley.typicodeclient.view.home;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import rx.Observable;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.model.PostResultsModel;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeResults;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomePresenterTest {
    private HomePresenterImpl sut;

    @Mock TypicodeFacade mockTypicodeFacade;
    @Mock HomeView mockView;
    @Mock TypicodeResults mockTypicodeResuls;
    @Mock TypicodeApplication mockApplication;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new HomePresenterImpl(mockApplication);

        verify(mockApplication, times(1)).inject(any());
    }

    public static ArrayList<PostResultsModel> allPosts() {
        ArrayList<PostResultsModel> postModels = new ArrayList<>();

        postModels.add(new PostResultsModel(1, 1, "title1", "body1"));
        postModels.add(new PostResultsModel(1, 2, "title2", "body2"));
        postModels.add(new PostResultsModel(1, 3, "title3", "body3"));
        postModels.add(new PostResultsModel(1, 4, "title4", "body4"));
        postModels.add(new PostResultsModel(1, 5, "title5", "body5"));
        postModels.add(new PostResultsModel(1, 6, "title6", "body6"));
        postModels.add(new PostResultsModel(1, 7, "title7", "body7"));
        postModels.add(new PostResultsModel(1, 8, "title8", "body8"));
        postModels.add(new PostResultsModel(1, 9, "title9", "body9"));
        postModels.add(new PostResultsModel(1, 10, "title10", "body10"));

        return postModels;
    }

    @Test
    public void test_whenTheInitialisationMethodIsCalled_shouldSeeTheViewMemberSetToTheViewPassedIn() {
        sut.initialise(mockView);

        Assert.assertEquals(mockView, sut.mView);
    }

    @Test
    public void test_whenTheDestroyMethodIsCalled_ifViewMemberIsSet_shouldSeeTheViewMemberSetToNull() {
        sut.destroy();

        Assert.assertEquals(null, sut.mView);
    }

    @Test
    public void test_whenTheGetPostsMethodIsCalled_ifTheTypicodeFacadeIsNotSet_shouldSeeTheGetPostMethodCalledOnTheTypicodeResultsClass() {
        sut.mTypicodeFacade = null;

        sut.getPosts();

        verify(mockTypicodeResuls, never()).getPosts();
    }

    @Test
    public void test_whenTheGetPostsMethodIsCalled_ifTheTypicodeFacadeIsSet_shouldSeeTheGetPostMethodCalledOnTheTypicodeResultsClass() {
        sut.mTypicodeFacade = mockTypicodeFacade;

        when(mockTypicodeResuls.getPosts()).thenReturn(Observable.just(allPosts()));
        when(mockTypicodeFacade.getTypicodeResults()).thenReturn(mockTypicodeResuls);

        sut.getPosts();

        verify(mockTypicodeResuls, times(1)).getPosts();
    }
}
