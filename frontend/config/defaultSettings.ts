import { Settings as LayoutSettings } from '@ant-design/pro-layout';

const Settings: LayoutSettings & {
  pwa?: boolean;
  logo?: string;
} = {
  navTheme: 'light',
  // 拂晓蓝
  primaryColor: '#1890ff',
  layout: 'mix',
  contentWidth: 'Fluid',
  fixedHeader: false,
  fixSiderbar: true,
  colorWeak: false,
  title: 'Themepark Queue System',
  pwa: false,
  logo: 'https://upload.wikimedia.org/wikipedia/commons/6/63/YoubeQ_app_icon.png',
  iconfontUrl: '',
};

export default Settings;
