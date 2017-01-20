package uk.me.paulriley.typicodeclient.view.detail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import rx.Observable;
import uk.me.paulriley.typicodeclient.TypicodeApplication;
import uk.me.paulriley.typicodeclient.services.model.CommentResultsModel;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeFacade;
import uk.me.paulriley.typicodeclient.services.typicode.TypicodeResults;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailPresenterTest {
    private static final int TEST_POST_ID = 1;

    private DetailPresenterImpl sut;

    @Mock TypicodeFacade mockTypicodeFacade;
    @Mock DetailView mockView;
    @Mock TypicodeResults mockTypicodeResuls;
    @Mock TypicodeApplication mockApplication;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new DetailPresenterImpl(mockApplication);

        sut.mTypicodeFacade = mockTypicodeFacade;
    }

    public static ArrayList<CommentResultsModel> allComments() {
        ArrayList<CommentResultsModel> commentModels = new ArrayList<>();

        commentModels.add(new CommentResultsModel(1, 1, "test name 1", "test email 1"
                , "test body 1"));
        commentModels.add(new CommentResultsModel(2, 2, "test name 2", "test email 2"
                , "test body 2"));
        commentModels.add(new CommentResultsModel(3, 3, "test name 3", "test email 3"
                , "test body 3"));
        commentModels.add(new CommentResultsModel(4, 4, "test name 4", "test email 4"
                , "test body 4"));
        commentModels.add(new CommentResultsModel(5, 5, "test name 5", "test email 5"
                , "test body 5"));
        commentModels.add(new CommentResultsModel(6, 6, "test name 6", "test email 6"
                , "test body 6"));
        commentModels.add(new CommentResultsModel(7, 7, "test name 7", "test email 7"
                , "test body 7"));
        commentModels.add(new CommentResultsModel(8, 8, "test name 8", "test email 8"
                , "test body 8"));
        commentModels.add(new CommentResultsModel(9, 9, "test name 9", "test email 9"
                , "test body 9"));
        commentModels.add(new CommentResultsModel(10, 10, "test name 10", "test email 10"
                , "test body 10"));

        return commentModels;
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
    public void test_whenTheGetCommenttsMethodIsCalled_ifTheTypicodeFacadeIsNotSet_shouldSeeTheGetCommentMethodCalledOnTheTypicodeResultsClass() {
        sut.mTypicodeFacade = null;

        sut.getComments(TEST_POST_ID);

        verify(mockTypicodeResuls, never()).getPostComments(TEST_POST_ID);
    }

    @Test
    public void test_whenTheGetCommentsMethodIsCalled_ifTheTypicodeFacadeIsSet_shouldSeeTheGetCommentMethodCalledOnTheTypicodeResultsClass() {
        sut.mTypicodeFacade = mockTypicodeFacade;

        when(mockTypicodeResuls.getPostComments(anyInt())).thenReturn(Observable.just(allComments()));
        when(mockTypicodeFacade.getTypicodeResults()).thenReturn(mockTypicodeResuls);

        sut.getComments(TEST_POST_ID);

        verify(mockTypicodeResuls, times(1)).getPostComments(TEST_POST_ID);
    }
}
