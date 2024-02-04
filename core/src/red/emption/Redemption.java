package red.emption;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import red.emption.FilesLoader.FileLoader;
import red.emption.managers.GameInputProcessor;
import red.emption.managers.GameKeys;
import red.emption.screens.GameScreen;
import red.emption.screens.LoadingScreen;

public class Redemption extends Game{

	public FileLoader loader;

	@Override
	public void create() {
		loader = new FileLoader();
		setScreen(new GameScreen(this));
		Gdx.input.setInputProcessor(new GameInputProcessor());

	}
	@Override
	public void render() {

		super.render();

		if (GameKeys.isPressed(GameKeys.SPACE)){
			Gdx.app.log("key","oo");
		}
		GameKeys.update();
		/*if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ) {
			Gdx.app.exit();
		}*/
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}

	@Override
	public void pause() {
		super.pause();

	}

}
